package micronaut.tut;

import javax.inject.Inject;
import javax.validation.Valid;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.RequestBean;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.reactivex.rxjava3.core.Single;


@Controller("/items")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class ItemController {

  @Inject
  ItemRepository itemRepository;


  @Get()
  Single<Item[]> getAllItems() {
    return Single.just(this.itemRepository.getAllItemsAsArray());
  }

  @Post
  Item addItem(@Valid @RequestBean Item item) {
    return this.itemRepository.addItem(item);
  }

}
