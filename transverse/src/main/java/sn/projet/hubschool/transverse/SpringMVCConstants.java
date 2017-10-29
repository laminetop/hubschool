package sn.projet.hubschool.transverse;

public final class SpringMVCConstants {

    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_MANAGER = "ROLE_MANAGER";
    public static final String ROLE_PROMOTEUR = "ROLE_PROMOTEUR";
    public static final String ROLE_CUSTUMER = "ROLE_USER";

    public static final String PROPERTY_BOOTSTRAP = "db.bootstrapData";
    public static final String PROPERTY_BOOTSTRAP_USER = "db.bootstrapData.user";

    public static final String STATUS_OK = "ok";
    public static final String STATUS_NO = "no";
    public static final String STATUS_WARNING = "warn";

    public static final Object UNITED_STATES = "us";
    public static final Object CANADA = "ca";

    public static final String MSG_EROR_BILLET_INCOHERENCE_EVENEMENT = "error.billet.validation.inchoerecebilletevent";
    public static final String MSG_EROR_BILLET_INCOHERENCE_USER = "error.billet.validation.inchoerecebilletuser";
    public static final String MSG_EROR_BILLET_VALIDATION_INCOHERENCE_DATE = "error.billet.validation.dateincorrect";
    public static final String MSG_EROR_BILLET_DEJA_UTILISE = "error.billet.validation.dejautilise";
    public static final String MSG_TICKET_NOT_EXIST = "error.ticket.not.exist";

    public static final String MSG_EVENT_NOT_EXIST = "error.event.not.exist";
    public static final String MSG_EVENT_TICKET_UNAVAILABLE = "error.event.tickets.unavailable";
    ;

    public static final String MSG_ACCOUNNT_INSUFFICIENT = "error.payment.account.insufficient";


    public static final String MSG_USER_NOT_EXIST = "error.user.not.exist";

    public static final String MSG_SENT_MAIL_AND_SMS_OK = "sent.mail.sms.ok";

    public static final String MSG_SENT_MAIL_OK = "sent.mail.ok";
    public static final String MSG_SENT_SMS_OK = "sent.sms.ok";

    public static final String ENV_DEV = "dev";
    public static final String ENV_INT = "int";

    public static final String DATE_FORMAT = "ddMMyyyy";


    private SpringMVCConstants() {
    }
}
