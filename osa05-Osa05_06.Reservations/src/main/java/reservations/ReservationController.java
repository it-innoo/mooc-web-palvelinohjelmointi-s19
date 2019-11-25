package reservations;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReservationController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @GetMapping("/reservations")
    public String listReservations(Model model) {
        model.addAttribute("reservations", reservationRepository.findAll());
        return "reservations";
    }

    @PostMapping("/reservations")
    public String addReservation(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate reservationFrom,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate reservationTo) {
                if (reservationFrom != null && reservationTo != null
                && reservationRepository.findOverlappingReservations(reservationFrom, reservationTo).isEmpty()) {
            Reservation r = new Reservation();
            r.setReservationFrom(reservationFrom);
            r.setReservationTo(reservationTo);
                    System.out.println("From: "+reservationFrom);
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
                    System.out.println("User: "+ username);
            r.setUser(accountRepository.findByUsername(username));

            reservationRepository.save(r);
        }

        return "redirect:/reservations";
    }

}
