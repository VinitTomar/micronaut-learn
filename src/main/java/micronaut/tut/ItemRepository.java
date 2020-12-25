package micronaut.tut;

import java.util.Arrays;
import java.util.Collections;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Single;

import java.util.concurrent.ConcurrentHashMap;

@Singleton
public class ItemRepository extends ConcurrentHashMap<String,Item> {

	private static final long serialVersionUID = 1L;

	@PostConstruct
	public  void init() {
		this.put("1", new Item("1","Item one","description for Item One"));
		this.put("2", new Item("2","Item two","description for Item two"));
		this.put("3", new Item("3","Item three","description for Item three"));
		this.put("4", new Item("4","Item four","description for Item four"));
  }
  
  Item[] getAllItemsAsArray() {
    Object[] objArr = Collections.list(this.elements()).toArray();
    return Arrays.copyOf(objArr, objArr.length, Item[].class);
  }
  
  Item addItem(Item item) {
    this.put(item.id, item);
    return item;
  }

  Single<Boolean> isIdAvailableAsync(String id) {
    if (this.get(id) == null) {
      return Single.just(true);
    }

    return Single.just(false);
  }

}
