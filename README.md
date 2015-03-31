# AndroidEndlessAdapter

AndroidAndlessAdapter is an listview adapter library project that support load automaticly more data. When you scroll to bottom on listview it decide to load more data.

# Usage

To use `EndlessAdapter`, you need to create an adapter that extend `EndlessAdapter`. Specify a view to show on loading placeholder. Load data on `loadMoreData()` method if you need more data to show. When you receive data to show, you need to call `notifyAdapter(List<String> list)` method. Use `getRowView()` method as normal `Adapter`'s `getView()` method.
