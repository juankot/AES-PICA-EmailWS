package co.edu.javeriana.pica.kallsonys.facade;

import co.edu.javeriana.pica.kallsonys.business.EmailBusiness;
import co.edu.javeriana.pica.kallsonys.dto.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailFacade {

    @Autowired
    EmailBusiness emailBusiness;

    public void sendEmail(Email email) throws Exception {
        emailBusiness.sendEmail(email);
    }
}
