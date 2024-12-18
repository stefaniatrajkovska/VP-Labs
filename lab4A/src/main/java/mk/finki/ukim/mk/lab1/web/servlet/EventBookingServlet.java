package mk.finki.ukim.mk.lab1.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab1.service.EventBookingService;
import mk.finki.ukim.mk.lab1.service.EventService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;


@WebServlet(name = "event-booking-servlet", urlPatterns = "/servlet/events/event-booking")
public class EventBookingServlet extends HttpServlet {

    private final EventService eventService;
    private final SpringTemplateEngine springTemplateEngine;


    public EventBookingServlet(SpringTemplateEngine springTemplateEngine, EventService eventService, SpringTemplateEngine springTemplateEngine1) {
        this.eventService = eventService;
        this.springTemplateEngine = springTemplateEngine1;
    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String queryString = req.getParameter("bookingSearch");
//        List<SavedBooking> bookingsToSend = eventService.getSavedBookings().stream()
//                .filter(booking -> booking.getEventName().toLowerCase().contains(queryString.toLowerCase()))
//                .toList();
//
//        IWebExchange iWebExchange = JakartaServletWebApplication.buildApplication(req.getServletContext()).buildExchange(req, resp);
//        WebContext context = new WebContext(iWebExchange);
//        context.setVariable("savedBookingList", bookingsToSend);
//        templateEngine.process("bookingConfirmation.html", context, resp.getWriter());
//    }

    // koga birame nastan i numTickets i kliknuvame submit, prakjame POST baranje do /eventBooking
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req,resp);
        WebContext context = new WebContext(webExchange);

        // povtorno ni treba thymeleaf za da mu gi pratime na bookingConfirmation.html podatocite koi sme gi dobile od baranjeto

        Integer numTickets = Integer.valueOf(req.getParameter("numTickets"));
        String eventName = req.getParameter("eventName");
        String ipAddress = req.getRemoteAddr();
        String attendeeName = req.getHeader("User-Agent");

        //eventBookingService.placeBooking(ipAddress,attendeeName,eventName,numTickets);

        context.setVariable("ipAddress", ipAddress);
        context.setVariable("attendeeName", attendeeName);
        context.setVariable("eventName", eventName);
        context.setVariable("numTickets", numTickets);

        springTemplateEngine.process("bookingConfirmation.html", context, resp.getWriter()); // go obrabotuva Thymeleaf sablonot - bookingConfirmation.html


    }
}
