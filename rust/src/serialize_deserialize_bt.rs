use std::fmt;
use std::iter::Peekable;
use std::str::FromStr;
use std::str::Split;

#[derive(Debug, PartialEq, Eq)]
struct TreeNode {
    val: i32,
    left: Option<Box<TreeNode>>,
    right: Option<Box<TreeNode>>,
}

impl TreeNode {
    fn new(val: i32) -> Self {
        TreeNode {
            val,
            left: None,
            right: None,
        }
    }
}

impl fmt::Display for TreeNode {
    fn fmt(&self, f: &mut fmt::Formatter) -> fmt::Result {
        write!(f, "{}", self.val)?;
        if self.left.is_some() || self.right.is_some() {
            write!(
                f,
                "({},{})",
                self.left.as_ref().map_or("", |node| node.to_string()),
                self.right.as_ref().map_or("", |node| node.to_string())
            )?;
        }
        Ok(())
    }
}

impl FromStr for TreeNode {
    type Err = ();

    fn from_str(s: &str) -> Result<Self, Self::Err> {
        fn parse_tree<'a, I: Iterator<Item = &'a str>>(
            iter: &mut Peekable<I>,
        ) -> Option<Box<TreeNode>> {
            if let Some(val_str) = iter.next() {
                if val_str.is_empty() {
                    return None;
                }
                let val = val_str.parse::<i32>().ok()?;
                let left = parse_tree(iter);
                let right = parse_tree(iter);
                Some(Box::new(TreeNode { val, left, right }))
            } else {
                None
            }
        }
        parse_tree(&mut s.split(',').peekable()).ok_or(())
    }
}
