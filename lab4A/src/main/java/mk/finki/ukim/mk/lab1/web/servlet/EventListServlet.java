package mk.finki.ukim.mk.lab1.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab1.model.Event;
import mk.finki.ukim.mk.lab1.service.EventService;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;


@WebServlet(name = "event-list-servlet", urlPatterns = "/servlet/events/events-list")
public class EventListServlet extends HttpServlet {
    private final EventService eventService;
    private final SpringTemplateEngine springTemplateEngine;

    public EventListServlet(EventService eventService, SpringTemplateEngine springTemplateEngine) {
        this.eventService = eventService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext webContext = new WebContext(webExchange);
        webContext.setVariable("events", this.eventService.listAll());
        // na html mu prakjame promenlivi


        String searchByName = req.getParameter("name");
        String searchByRating = req.getParameter("rating");



        if (searchByName != null && searchByName.isEmpty()) {
            searchByName = null;
        }
        // ако name параметар не е null односно е побаран како параметар а е празен, тогаш стави го да биде null

        if (searchByRating != null && searchByRating.isEmpty()) {
            searchByRating = null;
        }

        // исто со rating, ставаме null доколку е празен

        if (searchByName != null && searchByRating == null) {
            webContext.setVariable("events", eventService.searchByName(searchByName));
            // ако внесуваме параметар за пребарување само за име, а не и за рејтинг, пребаруваме по име

        } else if (searchByName == null && searchByRating != null) {
            webContext.setVariable("events", eventService.searchByRating(Double.parseDouble(searchByRating)));

        } else if (searchByName != null && searchByRating != null) {
            webContext.setVariable("events", eventService.searchByNameAndRating(searchByName, Double.valueOf(searchByRating)));
            // ако и двете се различни од null, пребарува и за двете
        } else {
            webContext.setVariable("events", eventService.listAll());

        }

        springTemplateEngine.process("listEvents.html", webContext,resp.getWriter());

    }

}
