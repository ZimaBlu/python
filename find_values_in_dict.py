def func(online_count):
    count = sum(value == 'online' for value in online_count.values())
    return count


statuses = {
    "Alice": "online",
    "Eve": "online",
    "Bob": "offline",
}
print(func(statuses))
