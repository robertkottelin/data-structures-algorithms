
use std::convert::Infallible;
use std::sync::{Arc, Mutex};
use std::collections::HashSet;

// Import necessary modules from the `hyper` crate. The fastest HTTP for rust
use hyper::{Body, Request, Response, Server, StatusCode};
use hyper::service::{make_service_fn, service_fn};
use hyper::Method;

// Define a type alias for Items to make the code more readable.
// Items is a thread-safe reference-counted container for a set of strings.
type Items = Arc<Mutex<HashSet<String>>>;

// Define the API handler function.
// This function is called for each incoming request and is responsible for
// processing the request and generating a response.
async fn api_handler(items: Items, req: Request<Body>) -> Result<Response<Body>, Infallible> {
    let (method, uri) = (req.method().clone(), req.uri().clone());
    let response = match (method, uri.path()) {
        // If the request is a GET request to "/items", return the list of items.
        (Method::GET, "/items") => {
            let items = items.lock().unwrap();
            let items = items.iter().cloned().collect::<Vec<String>>().join(", ");
            let body = format!("Items: [{}]", items);
            Response::new(Body::from(body))
        }
        // If the request is a POST request to "/items", add the new item to the list.
        (Method::POST, "/items") => {
            let item = uri.query().unwrap_or("").to_string();
            if !item.is_empty() {
                items.lock().unwrap().insert(item);
                Response::new(Body::from("Item added"))
            } else {
                let mut response = Response::new(Body::from("Empty item not allowed"));
                *response.status_mut() = StatusCode::BAD_REQUEST;
                response
            }
        }
        // For all other requests, return a 404 Not Found response.
        _ => {
            let mut response = Response::new(Body::from("Not found"));
            *response.status_mut() = StatusCode::NOT_FOUND;
            response
        }
    };

    Ok(response)
}

// Define the main function which runs the HTTP server.
#[tokio::main]
async fn main() {
    // Initialize the items container.
    let items: Items = Arc::new(Mutex::new(HashSet::new()));
    // Set the address and port for the server to listen on.
    let addr = ([127, 0, 0, 1], 3000).into();
    // Define the service factory, which creates a new service for each connection.
    let make_svc = make_service_fn(move |_| {
        // Clone the items container for each service instance.
        let items = items.clone();
        async move {
            Ok::<_, Infallible>(service_fn(move |req| {
                // Clone the items container for each request.
                let items = items.clone();
                // Call the API handler for each request.
                api_handler(items, req)
            }))
        }
    });

    // Create the server, bind it to the address, and serve incoming requests.
    let server = Server::bind(&addr).serve(make_svc);
    println!("Server started on http://{}", addr);
    // Run the server and handle any errors.
    if let Err(e) = server.await {
        eprintln!("Server error: {}", e);
    }
}


/*
REACT FRONTEND EXAMPLE

import { useState, useEffect } from "react";
import "./App.css";

const API_URL = "http://127.0.0.1:3000";

async function fetchItems() {
  const response = await fetch(`${API_URL}/items`);
  const text = await response.text();
  const items = text.slice(8, -1).split(", ");
  return items;
}

async function addItem(item) {
  const response = await fetch(`${API_URL}/items?${item}`, { method: "POST" });
  return response.ok;
}

function App() {
  const [items, setItems] = useState([]);
  const [inputValue, setInputValue] = useState("");

  useEffect(() => {
    const updateItems = async () => {
      const items = await fetchItems();
      setItems(items);
    };
    updateItems();
  }, []);

  const handleAddItem = async (event) => {
    event.preventDefault();
    const item = inputValue.trim();

    if (item) {
      await addItem(item);
      setInputValue("");
      setItems(await fetchItems());
    }
  };

  return (
    <div className="App">{items}</div>     
  )

*/