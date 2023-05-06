fn linear_search(haystack: &[i32], needle: i32) -> bool {
    for value in haystack {
        if value == &needle {
            return true;
        }
    }

    return false;
}