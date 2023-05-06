use std::cmp::Reverse;
use std::collections::BinaryHeap;

fn merge_k_sorted_lists(lists: Vec<Vec<i32>>) -> Vec<i32> {
    let mut heap = BinaryHeap::new();
    let mut result = vec![];

    for (i, list) in lists.into_iter().enumerate() {
        if !list.is_empty() {
            heap.push((Reverse(list[0]), i, 0));
        }
    }

    let mut lists = lists;
    while let Some((Reverse(value), list_index, element_index)) = heap.pop() {
        result.push(value);

        if element_index + 1 < lists[list_index].len() {
            heap.push((
                Reverse(lists[list_index][element_index + 1]),
                list_index,
                element_index + 1,
            ));
        }
    }

    result
}
