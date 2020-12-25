package micronaut.tut;

import io.micronaut.http.annotation.*;

import javax.validation.Valid;

@Controller("/issues")
public class IssuesController {

    @Get("/{number}{?minPrice,maxPrice}")
    public String index(@Valid @RequestBean IssueTicketBean issueTicketBean) {
        return "By Issue # " + issueTicketBean.number + " with minPrice= " + issueTicketBean.minPrice
                + ", with maxPrice= " + issueTicketBean.maxPrice;
    }
}