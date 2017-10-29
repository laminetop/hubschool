package sn.projet.utils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Utilitaire pour les dates.
 */
public final class DateUtils {

    /**
     * Format pour la base de données yyyy-MM-dd hh:mm:ss.SSSSSS.
     */
    public static final String FORMAT_DATE_BASE_DE_DONNEES_TIMESTAMP = "yyyy-MM-dd HH:mm:ss.SSSSSS";
    /**
     * Format pour l'affichage uniquement dd/MM/yyyy.
     */
    public static final String FORMAT_AFFICHAGE_DATE = "dd/MM/yyyy";
    /**
     * Format pour tout les traitement yyyy-MM-dd.
     */
    public static final String FORMAT_DATE = "yyyy-MM-dd";
    /**
     * Format pour l'affichage du bas de page dd/MM/yyyy | HH:mm:ss.
     */
    public static final String FORMAT_DATE_BAS_PAGE = "dd/MM/yyyy HH:mm:ss";
    /**
     * Format pour la base de données yyyyMMddHHmmss.
     */
    public static final String FORMAT_DATE_BASE_DE_DONNEES = "yyyyMMddHHmmss";
    /**
     * Format pour tout les traitement yyyy-MM-dd'T'HH:mm:ss,SZ.
     */
    public static final String FORMAT_ISO_DATE = "yyyy-MM-dd'T'HH:mm:ss,SZ";
    /**
     * Chiffre 2.
     */
    public static final int DEUX = 2;
    /**
     * Chiffre 3.
     */
    public static final int TROIS = 3;
    /**
     * Chiffre 4.
     */
    public static final int QUATRE = 4;
    /**
     * Chiffre 5.
     */
    public static final int CINQ = 5;
    /**
     * Chiffre 6.
     */
    public static final int SIX = 6;
    /**
     * Chiffre 7.
     */
    public static final int SEPT = 7;
    /**
     * Chiffre 8.
     */
    public static final int HUIT = 8;
    /**
     * Nombre 10.
     */
    public static final int DIX = 10;
    /**
     * Nombre 11.
     */
    public static final int ONZE = 11;
    /**
     * Nombre 12.
     */
    public static final int DOUZE = 12;
    /**
     * Nombre 13.
     */
    public static final int TREIZE = 13;
    /**
     * Nombre 14.
     */
    public static final int QUATORZE = 14;
    /**
     * Nombre 15.
     */
    public static final int QUINZE = 15;
    /**
     * Nombre 19.
     */
    public static final int DIX_NEUF = 19;
    /**
     * Nombre 20.
     */
    public static final int VINGT = 20;
    /**
     * Nombre 21.
     */
    public static final int VINGT_ET_UN = 21;
    /**
     * Nombre 24.
     */
    public static final int VINGT_QUATRE = 24;
    /**
     * Nombre 25.
     */
    public static final int VINGT_CINQ = 25;
    /**
     * Nombre 28.
     */
    public static final int VINGT_HUIT = 28;
    /**
     * Nombre 29.
     */
    public static final int VINGT_NEUF = 29;
    /**
     * Nombre 30.
     */
    public static final int TRENTE = 30;
    /**
     * Nombre 31.
     */
    public static final int TRENTE_ET_UN = 31;
    /**
     * Nombre 39.
     */
    public static final int TRENTE_NEUF = 39;
    /**
     * Nombre 44.
     */
    public static final int QUARANTE_QUATRE = 44;
    /**
     * Nombre 50.
     */
    public static final int CINQUANTE = 50;
    /**
     * Nombre 100.
     */
    public static final int CENT = 100;

    /**
     * Pas de constructeur public pour cette classe.
     */
    private DateUtils() {
    }

    /**
     * Convertit la date <code>cal</code> sous la forme {@link #FORMAT_DATE}.
     *
     * @param cal le calendar à convertir
     * @return la chaine au format {@link #FORMAT_DATE}, <code>null</code> sinon
     */
    public static final String getAAAAMMJJ(final Calendar cal) {
        if (cal == null) {
            return null;
        }
        Date date = cal.getTime();
        return getDateFormat(FORMAT_DATE).format(date);
    }

    /**
     * Convertit la date <code>cal</code> sous la forme {@link #FORMAT_DATE}.
     *
     * @param date la date à convertir
     * @return la chaine au format {@link #FORMAT_DATE}, <code>null</code> sinon
     */
    public static final String getAAAAMMJJ(final Date date) {
        if (date == null) {
            return null;
        }

        return getDateFormat(FORMAT_DATE).format(date);
    }

    /**
     * Convertit la chaine <code>aaaammjj</code> au format {@link #FORMAT_DATE} en la date
     * correspondante.
     *
     * @param aaaammjj la date au format {@link #FORMAT_DATE}
     * @return l'objet <code>java.util.Calendar</code> correspondant à la chaine
     * <code>aaaammjj</code>, <code>null</code> sinon
     */
    public static final Date getCalendarISO(final String aaaammjj) {
        try {
            if (aaaammjj == null) {
                return null;
            }
            Date date = getDateFormat(FORMAT_DATE).parse(aaaammjj);
            Calendar calendar = getDate();
            calendar.setTime(date);
            return calendar.getTime();
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }


    /**
     * Convertit la chaine <code>yyyyMMddHHmmss</code> au format {@link
     * #FORMAT_DATE_BASE_DE_DONNEES} en la date correspondante.
     *
     * @param yyyyMMddHHmmss la date au format {@link #FORMAT_DATE_BASE_DE_DONNEES}
     * @return l'objet <code>java.util.Calendar</code> correspondant à la chaine
     * <code>yyyyMMddHHmmss</code>, <code>null</code> sinon
     */
    public static final Calendar getCalendaryyyyMMddHHmmss(final String yyyyMMddHHmmss) {
        try {
            Date date = getDateFormat(FORMAT_DATE_BASE_DE_DONNEES).parse(yyyyMMddHHmmss);
            Calendar calendar = getDate();
            calendar.setTime(date);
            return calendar;
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Retourne une instance de l'objet <code>java.util.Calendar</code> vide (0000-00-00T00:00:00).
     *
     * @return une instance de l'objet <code>java.util.Calendar</code> vide
     */
    public static final Calendar getDate() {
        Calendar ret = Calendar.getInstance();
        ret.clear();
        return ret;
    }

    /**
     * Retourne une chaine au format {@link #FORMAT_DATE_BAS_PAGE} représentant la date courante.
     *
     * @return la date courante au format {@link #FORMAT_DATE_BAS_PAGE}
     */
    public static final String getDateBasDePage() {
        return getDateFormat(FORMAT_DATE_BAS_PAGE).format(getDateCourante().getTime());
    }

    /**
     * Retourne la date courante <b>à la Time Zone du Système</b>.
     *
     * @return la date courante <b>à la Time Zone du Système</b>
     */
    public static final Calendar getDateCourante() {
        return Calendar.getInstance();
    }

    /**
     * Retourne une instance de l'objet <code>SimpleDateFormat</code> représentant le pattern
     * donné.
     *
     * @param pattern le format
     * @return l'instance de l'objet <code>SimpleDateFormat</code> représentant le pattern donné
     */
    public static final SimpleDateFormat getDateFormat(final String pattern) {
        return new SimpleDateFormat(pattern);
    }

    /**
     * Retourne une chaine sous la forme {@link #FORMAT_DATE_BASE_DE_DONNEES} représentant le
     * <code>calendar</code>.
     *
     * @param calendar le calendar à convertir
     * @return la chaine sous la forme {@link #FORMAT_DATE_BASE_DE_DONNEES} représentant le
     * <code>calendar</code>
     */
    public static final String getDateyyyyMMddHHmmss(final Calendar calendar) {
        return getDateFormat(FORMAT_DATE_BASE_DE_DONNEES).format(calendar.getTime());
    }

    /**
     * Convertit la date <code>cal</code> sous la forme {@link #FORMAT_ISO_DATE}.
     *
     * @param cal le calendar à convertir
     * @return la chaine au format {@link #FORMAT_ISO_DATE}, <code>null</code> sinon
     */
    public static final String getISODate(final Calendar cal) {
        if (cal == null) {
            return null;
        }
        Date date = cal.getTime();
        return getDateFormat(FORMAT_ISO_DATE).format(date);
    }

    /**
     * Convertie la date passe en parametre avec un format.
     *
     * @param cal la date
     * @param dt  le format
     * @return String
     */
    public static String getDateFormate(final Calendar cal, final DateFormat dt) {
        Date date = cal.getTime();
        return dt.format(date);
    }

    /**
     * Convertie la date passe en parametre avec un format.
     *
     * @param date la date
     * @param dt   le format
     * @return String
     */
    public static String getDateFormate(final Date date, final DateFormat dt) {
        return dt.format(date);
    }

    /**
     * <B>Uniquement pour l'affichage</B> Convertie la date passe en parametre sous la forme
     * JJ/MM/AAAA.
     *
     * @param cal la date à convertir au format JJ/MM/AAAA
     * @return JJ/MM/AAAA
     */
    public static String getJJMMAAAAAffichage(final Calendar cal) {
        Date date = cal.getTime();
        return getDateFormat(FORMAT_AFFICHAGE_DATE).format(date);
    }

    /**
     * <B>Uniquement pour l'affichage</B> Convertie une chaine JJ/MM/AAAA en la date.
     *
     * @param jjmmaaaa la date au format JJ/MM/AAAA à convertir en <code>Calendar</code>
     * @return Calendar
     */
    public static Calendar getCalendarAffichage(final String jjmmaaaa) {
        try {
            Date date = getDateFormat(FORMAT_AFFICHAGE_DATE).parse(jjmmaaaa);
            Calendar calendar = getDate();
            calendar.setTime(date);
            return calendar;
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * <B>Uniquement pour l'affichage</B> Convertie une chaine JJ/MM/AAAA en la date.
     *
     * @param jjmmaaaa la date au format JJ/MM/AAAA à convertir en <code>Date</code>
     * @return Date
     */
    public static Date getDateAffichage(final String jjmmaaaa) {
        try {
            Date date = getDateFormat(FORMAT_AFFICHAGE_DATE).parse(jjmmaaaa);
            return date;
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Converti la date sous le format yyyy-MM-dd HH:mm:ss.SSSSSS.
     *
     * @param calendar la date à convertir sous le format yyyy-MM-dd HH:mm:ss.SSSSSS
     * @return la date au format yyyy-MM-dd HH:mm:ss.SSSSSS
     */
    public static String getDateAsTimeStamp(final Calendar calendar) {
        return getDateFormat(FORMAT_DATE_BASE_DE_DONNEES_TIMESTAMP).format(calendar.getTime());
    }

    /**
     * Retourne l'année en cours <b>à la Time Zone du Système</b>.
     *
     * @return l'année au format AAAA
     */
    public static String getAAAA() {
        return Integer.toString(getAnneeCourante());
    }

    /**
     * Retourne l'année en cours <b>à la Time Zone du Système</b>.
     *
     * @return l'année au format AAAA
     */
    public static int getAnneeCourante() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * Retourne la date du jour sous la forme AAAA-MM-JJ <b>à la Time Zone du Système</b>.
     *
     * @return la date du jour au format AAAA-MM-JJ
     */
    public static String getAAAAMMJJ() {
        return getAAAAMMJJ(Calendar.getInstance());
    }

    /**
     * La date au premier Janvier de l'année en cours à 00:00 (reset de l'heure).
     *
     * @return le <code>Calendar</code> qui représente le 1er Janvier de l'année en cours
     */
    public static Calendar get1erJanvier() {
        Calendar c = getDateCourante();
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        c.clear(Calendar.AM_PM);
        c.clear(Calendar.HOUR_OF_DAY);
        return c;
    }

    /**
     * La date au premier Janvier de l'année passée/futur à 00:00 (reset de l'heure).
     *
     * @param annee l'année auquelle il faut calculer la date du 1er janvier
     * @return le <code>Calendar</code> qui représente le 1er Janvier de l'année <code>annee</code>
     */
    public static Calendar get1erJanvier(final int annee) {
        Calendar c = get1erJanvier();
        c.set(Calendar.YEAR, annee);
        return c;
    }

    /**
     * La date au 31 Decembre de l'année en cours.
     *
     * @return Calendar
     */
    public static Calendar get31Decembre() {
        Calendar c = getDateCourante();
        c.set(Calendar.MONTH, Calendar.DECEMBER);
        c.set(Calendar.DAY_OF_MONTH, TRENTE_ET_UN);
        c.clear(Calendar.AM_PM);
        c.clear(Calendar.HOUR_OF_DAY);
        return c;
    }

    /**
     * La date au 31 Decembre de l'année passée/futur.
     *
     * @param annee l'année auquelle il faut calculer la date du 31 décembre
     * @return le <code>Calendar</code> qui représente le 31 décembre de l'année <code>annee</code>
     */
    public static Calendar get31Decembre(final int annee) {
        Calendar c = get31Decembre();
        c.set(Calendar.YEAR, annee);
        return c;
    }

    /**
     * Mise à 00:00 de la date passé (reset de l'heure).
     *
     * @param date Calendar à mettre à 00:00
     */
    public static void miseAZeroHeure(final Calendar date) {
        date.set(Calendar.HOUR, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
    }

    /**
     * Wrapper after pour les calendar avec une précision JOUR.
     *
     * @param c1 Calendar 1
     * @param c2 Calendar 2
     * @return idem que pour la methode after
     * @see java.util.Calendar#after(Object)
     * @since 1.0.7
     */
    public static boolean afterPrecisionJour(final Calendar c1, final Calendar c2) {
        Calendar c11 = (Calendar) c1.clone();
        Calendar c22 = (Calendar) c2.clone();

        c11.set(Calendar.HOUR, 0);
        c11.set(Calendar.MINUTE, 0);
        c11.set(Calendar.SECOND, 0);
        c11.set(Calendar.MILLISECOND, 0);

        c11.clear(Calendar.AM_PM);
        c11.clear(Calendar.HOUR_OF_DAY);

        c22.set(Calendar.HOUR, 0);
        c22.set(Calendar.MINUTE, 0);
        c22.set(Calendar.SECOND, 0);
        c22.set(Calendar.MILLISECOND, 0);

        c22.clear(Calendar.AM_PM);
        c22.clear(Calendar.HOUR_OF_DAY);

        return c11.after(c22);
    }

    /**
     * Wrapper after pour les calendar avec une précision JOUR.
     *
     * @param c1 Date 1
     * @param c2 Date 2
     * @return idem que pour la methode after
     * @see java.util.Date#after(Object)
     * @since 1.0.7
     */
    public static boolean afterPrecisionJour(final Date c1, final Date c2) {
        Calendar c11 = getDate();
        c11.setTime((Date) c1.clone());
        Calendar c22 = getDate();
        c22.setTime((Date) c2.clone());

        c11.set(Calendar.HOUR, 0);
        c11.set(Calendar.MINUTE, 0);
        c11.set(Calendar.SECOND, 0);
        c11.set(Calendar.MILLISECOND, 0);

        c11.clear(Calendar.AM_PM);
        c11.clear(Calendar.HOUR_OF_DAY);

        c22.set(Calendar.HOUR, 0);
        c22.set(Calendar.MINUTE, 0);
        c22.set(Calendar.SECOND, 0);
        c22.set(Calendar.MILLISECOND, 0);

        c22.clear(Calendar.AM_PM);
        c22.clear(Calendar.HOUR_OF_DAY);

        return c11.after(c22);
    }

    /**
     * Wrapper before pour les calendar avec une précision JOUR.
     *
     * @param c1 Calendar 1
     * @param c2 Calendar 2
     * @return idem que pour la methode after
     * @see java.util.Calendar#before(Object)
     * @since 1.0.7
     */
    public static boolean beforePrecisionJour(final Calendar c1, final Calendar c2) {
        Calendar c11 = (Calendar) c1.clone();
        Calendar c22 = (Calendar) c2.clone();

        c11.set(Calendar.HOUR, 0);
        c11.set(Calendar.MINUTE, 0);
        c11.set(Calendar.SECOND, 0);
        c11.set(Calendar.MILLISECOND, 0);

        c11.clear(Calendar.AM_PM);
        c11.clear(Calendar.HOUR_OF_DAY);

        c22.set(Calendar.HOUR, 0);
        c22.set(Calendar.MINUTE, 0);
        c22.set(Calendar.SECOND, 0);
        c22.set(Calendar.MILLISECOND, 0);

        c22.clear(Calendar.AM_PM);
        c22.clear(Calendar.HOUR_OF_DAY);

        return c11.before(c22);
    }

    /**
     * Wrapper before pour les calendar avec une précision JOUR.
     *
     * @param c1 Date 1
     * @param c2 Date 2
     * @return idem que pour la methode after
     * @see java.util.Date#before(Object)
     * @since 1.0.7
     */
    public static boolean beforePrecisionJour(final Date c1, final Date c2) {
        Calendar c11 = getDate();
        c11.setTime((Date) c1.clone());
        Calendar c22 = getDate();
        c22.setTime((Date) c2.clone());

        c11.set(Calendar.HOUR, 0);
        c11.set(Calendar.MINUTE, 0);
        c11.set(Calendar.SECOND, 0);
        c11.set(Calendar.MILLISECOND, 0);

        c11.clear(Calendar.AM_PM);
        c11.clear(Calendar.HOUR_OF_DAY);

        c22.set(Calendar.HOUR, 0);
        c22.set(Calendar.MINUTE, 0);
        c22.set(Calendar.SECOND, 0);
        c22.set(Calendar.MILLISECOND, 0);

        c22.clear(Calendar.AM_PM);
        c22.clear(Calendar.HOUR_OF_DAY);

        return c11.before(c22);
    }

    /**
     * Wrapper compareTo pour les calendar avec une précision JOUR.
     *
     * @param c1 Calendar 1
     * @param c2 Calendar 2
     * @return idem que pour la methode after
     * @see java.util.Calendar#compareTo(Object)
     * @since 1.0.7
     */
    public static int compareToPrecisionJour(final Calendar c1, final Calendar c2) {
        Calendar c11 = (Calendar) c1.clone();
        Calendar c22 = (Calendar) c2.clone();

        c11.set(Calendar.HOUR, 0);
        c11.set(Calendar.MINUTE, 0);
        c11.set(Calendar.SECOND, 0);
        c11.set(Calendar.MILLISECOND, 0);

        c11.clear(Calendar.AM_PM);
        c11.clear(Calendar.HOUR_OF_DAY);

        c22.set(Calendar.HOUR, 0);
        c22.set(Calendar.MINUTE, 0);
        c22.set(Calendar.SECOND, 0);
        c22.set(Calendar.MILLISECOND, 0);

        c22.clear(Calendar.AM_PM);
        c22.clear(Calendar.HOUR_OF_DAY);

        return c11.compareTo(c22);
    }

    /**
     * Wrapper compareTo pour les calendar avec une précision JOUR.
     *
     * @param c1 Date 1
     * @param c2 Date 2
     * @return idem que pour la methode after
     * @see java.util.Date#compareTo(Object)
     * @since 1.0.7
     */
    public static int compareToPrecisionJour(final Date c1, final Date c2) {
        Calendar c11 = getDate();
        c11.setTime((Date) c1.clone());
        Calendar c22 = getDate();
        c22.setTime((Date) c2.clone());

        c11.set(Calendar.HOUR, 0);
        c11.set(Calendar.MINUTE, 0);
        c11.set(Calendar.SECOND, 0);
        c11.set(Calendar.MILLISECOND, 0);

        c11.clear(Calendar.AM_PM);
        c11.clear(Calendar.HOUR_OF_DAY);

        c22.set(Calendar.HOUR, 0);
        c22.set(Calendar.MINUTE, 0);
        c22.set(Calendar.SECOND, 0);
        c22.set(Calendar.MILLISECOND, 0);

        c22.clear(Calendar.AM_PM);
        c22.clear(Calendar.HOUR_OF_DAY);

        return c11.compareTo(c22);
    }

    /**
     * @return La date courante en Millis.
     */
    public static long getDateCouranteEnMillis() {
        return getDateCourante().getTimeInMillis();
    }

    /**
     * Calcule le nombre de jours calendaires entre deux dates Le decompte est fait incluant les
     * dates de debuyt et de fin.
     *
     * @param debut la date de début
     * @param fin   la date de fin
     * @return le nombre de jours calandaires calcules.
     */
    public static int getDureeJoursCalendairePleins(final Calendar debut, final Calendar fin) {

        int result = 0;
        boolean inverseResult = false;

        Calendar date1 = null;
        Calendar date2 = null;

        if (debut.equals(fin)) {
            return 1;
        }

        if (debut.compareTo(fin) < 0) {
            date1 = (Calendar) debut.clone();
            date2 = (Calendar) fin.clone();
        } else {
            date2 = (Calendar) debut.clone();
            date1 = (Calendar) fin.clone();
            inverseResult = true;
        }

        if (date1.get(Calendar.YEAR) == date2.get(Calendar.YEAR)) {
            result = date2.get(Calendar.DAY_OF_YEAR) - date1.get(Calendar.DAY_OF_YEAR) + 1;
        } else {
            int daydebut = date1.getActualMaximum(Calendar.DAY_OF_YEAR) - date1.get(Calendar.DAY_OF_YEAR) + 1;
            int dayfin = date2.get(Calendar.DAY_OF_YEAR);
            int daysbetween = 0;

            for (int i = date1.get(Calendar.YEAR) + 1; i < date2.get(Calendar.YEAR); i++) {

                Calendar yearbetween = Calendar.getInstance();
                yearbetween.set(Calendar.YEAR, i);
                daysbetween += yearbetween.getActualMaximum(Calendar.DAY_OF_YEAR);
            }
            result = daydebut + daysbetween + dayfin;
        }

        if (inverseResult) {
            result = result * -1;
        }

        return result;
    }

    /**
     * Calcule le nombre de jours calendaires entre deux dates.<br/> Le decompte est fait incluant
     * les dates de debut et de fin. Est pris en compte egalement si les dates de debut et de fin
     * sont entieres ou à moitié selon le flag AM/PM du Calendrier les representant. Si la date de
     * debut est flagée comme AM, elle represente un journée, si elle est flagés PM, elle represente
     * une demi-journée. Idem mais dans l'autre sens pour la date de fin : AM = une demi-journée, PM
     * = 1 journée.
     *
     * @param debut la date de début
     * @param fin   la date de fin
     * @return le nombre de jours calandaires calculés.
     */
    public static BigDecimal getDureeJoursCalendaire(final Calendar debut, final Calendar fin) {

        int result = 0;
        boolean inverseResult = false;

        Calendar date1 = null;
        Calendar date2 = null;

        if (debut.equals(fin)) {
            return BigDecimal.valueOf(CINQ, 1);
        }

        // si on est dans la même journée
        if ((debut.get(Calendar.YEAR) == fin.get(Calendar.YEAR)) && (debut.get(Calendar.MONTH) == fin.get(Calendar.MONTH))
                && (debut.get(Calendar.DAY_OF_MONTH) == fin.get(Calendar.DAY_OF_MONTH))) {

            if ((debut.get(Calendar.AM_PM) == Calendar.AM) && (fin.get(Calendar.AM_PM) == Calendar.PM)) {
                return BigDecimal.ONE;
            } else if ((debut.get(Calendar.AM_PM) == Calendar.AM) && (fin.get(Calendar.AM_PM) == Calendar.AM)) {
                return BigDecimal.valueOf(CINQ, 1);
            } else if ((debut.get(Calendar.AM_PM) == Calendar.PM) && (fin.get(Calendar.AM_PM) == Calendar.PM)) {
                return BigDecimal.valueOf(CINQ, 1);
            } else {
                return BigDecimal.ZERO;
            }
        }

        if (debut.compareTo(fin) < 0) {
            // debut < fin
            date1 = (Calendar) debut.clone();
            date2 = (Calendar) fin.clone();
        } else {
            // debut > fin
            date2 = (Calendar) debut.clone();
            date1 = (Calendar) fin.clone();
            inverseResult = true;
        }

        if (date1.get(Calendar.YEAR) == date2.get(Calendar.YEAR)) {
            result = date2.get(Calendar.DAY_OF_YEAR) - date1.get(Calendar.DAY_OF_YEAR) + 1;
        } else {
            int daydebut = date1.getActualMaximum(Calendar.DAY_OF_YEAR) - date1.get(Calendar.DAY_OF_YEAR) + 1;
            int dayfin = date2.get(Calendar.DAY_OF_YEAR);
            int daysbetween = 0;

            for (int i = date1.get(Calendar.YEAR) + 1; i < date2.get(Calendar.YEAR); i++) {

                Calendar yearbetween = Calendar.getInstance();
                yearbetween.set(Calendar.YEAR, i);
                daysbetween += yearbetween.getActualMaximum(Calendar.DAY_OF_YEAR);
            }

            result = daydebut + daysbetween + dayfin;

        }

        if (inverseResult) {
            result = result * -1;
        }

        BigDecimal res = new BigDecimal(result);
        if (date1.get(Calendar.AM_PM) == Calendar.PM) {
            res = res.subtract(BigDecimal.valueOf(CINQ, 1));
        }
        if (date2.get(Calendar.AM_PM) == Calendar.AM) {
            res = res.subtract(BigDecimal.valueOf(CINQ, 1));
        }

        return res;
    }

    /**
     * Calcule le nombre de jours calendaires (en float) entre deux dates.<br/> Le decompte est fait
     * incluant les dates de debut et de fin. Est pris en compte egalement si les dates de debut et
     * de fin sont entieres ou à moitié selon le flag AM/PM du Calendrier les representant. Si la
     * date de debut est flagée comme AM, elle represente un journée, si elle est flagés PM, elle
     * represente une demi-journée. Idem mais dans l'autre sens pour la date de fin : AM = une
     * demi-journée, PM = 1 journée.
     *
     * @param debut la date de début
     * @param fin   la date de fin
     * @return le nombre de jours calandaires calculés.
     */
    public static float getDureeJoursCalendaireEnFloat(final Calendar debut, final Calendar fin) {
        BigDecimal jours = getDureeJoursCalendaire(debut, fin);
        return jours.floatValue();
    }

    /**
     * Calcule le nombre de jour ouvrables entre deux dates, avec l'assumption qu'il y a 6 jours
     * ouvrables par semaine. Le decompte est fait incluant les dates de debuyt et de fin. Est pris
     * en compte egalement si les dates de debut et de fin sont entieres ou à moitié selon le flag
     * AM/PM du Calendrier les representant. Si la date de debut est flagée comme AM, elle
     * represente un journée, si elle est flagés PM, elle represente une demi-journée. Idem mais
     * dans l'autre sens pour la date de fin : AM = une demi-journée, PM = 1 journée.
     *
     * @param debut la date de début
     * @param fin   la date de fin
     * @return le nombre de jours ouvrables calcules.
     */
    public static BigDecimal getDureeJoursOuvrables(final Calendar debut, final Calendar fin) {

        int result = 0;

        int nbJours = getDureeJoursCalendairePleins(debut, fin);

        int nbWeeks = nbJours / SEPT;
        int remainder = nbJours % SEPT;

        if (remainder > 0) {
            int debutJour = debut.get(Calendar.DAY_OF_WEEK);
            int report = debutJour + remainder - 1;
            if (report > SEPT) { // si par dessus un dimanche
                remainder = remainder - 1;
            }
            if (debutJour == Calendar.SUNDAY) {
                remainder = remainder - 1;
            }
        }

        result = nbWeeks * SIX + remainder;

        BigDecimal res = new BigDecimal(result);

        List<Calendar> jourFeriesOuvrables = getJoursFeriesOuvrables(debut, fin);
        boolean debutEstFerie = false;
        boolean finEstFerie = false;

        if (nbJours == 1) {

            int debutJour = debut.get(Calendar.DAY_OF_WEEK);
            // si la date de debut est ouvrable
            if (debutJour != Calendar.SUNDAY) {
                Iterator<Calendar> iter = jourFeriesOuvrables.iterator();
                // on recherche si la date de debut est feriée
                while (iter.hasNext()) {
                    if (compareToPrecisionJour(iter.next(), debut) == 0) {
                        debutEstFerie = true;
                    }
                }
                if (debutEstFerie) {
                    // si oui, on soustrait une journée
                    res = res.subtract(BigDecimal.ONE);
                } else {
                    // si, non, on regarde s'il faut soustraite une demie journée.
                    if ((debut.get(Calendar.AM_PM) == Calendar.AM && fin.get(Calendar.AM_PM) == Calendar.AM)
                            || (debut.get(Calendar.AM_PM) == Calendar.PM && fin.get(Calendar.AM_PM) == Calendar.PM)) {
                        res = res.subtract(BigDecimal.valueOf(CINQ, 1));
                    }
                }
            }

        } else {
            int debutJour = debut.get(Calendar.DAY_OF_WEEK);
            // si la date de debut est ouvrable
            if (debutJour != Calendar.SUNDAY) {
                Iterator<Calendar> iter = jourFeriesOuvrables.iterator();
                // on recherche si la date de debut est feriée
                while (iter.hasNext()) {
                    if (compareToPrecisionJour(iter.next(), debut) == 0) {
                        debutEstFerie = true;
                    }
                }
                if (debutEstFerie) {
                    // si oui, on soustrait une journée
                    res = res.subtract(BigDecimal.ONE);
                } else {
                    // si, non, on regarde s'il faut soustraite une demie journée.
                    if (debut.get(Calendar.AM_PM) == Calendar.PM) {
                        res = res.subtract(BigDecimal.valueOf(CINQ, 1));
                    }
                }
            }
            int finJour = fin.get(Calendar.DAY_OF_WEEK);
            // si la date de fin est ouvrable
            if (finJour != Calendar.SUNDAY) {
                Iterator<Calendar> iter = jourFeriesOuvrables.iterator();
                // on recherche si la date de fin est feriée
                while (iter.hasNext()) {
                    if (compareToPrecisionJour(iter.next(), fin) == 0) {
                        finEstFerie = true;
                    }
                }
                if (finEstFerie) {
                    // si oui, on soustrait une journée
                    res = res.subtract(BigDecimal.ONE);
                } else {
                    // si, non, on regarde s'il faut soustraite une demie journée.
                    if (fin.get(Calendar.AM_PM) == Calendar.AM) {
                        res = res.subtract(BigDecimal.valueOf(CINQ, 1));
                    }
                }
            }

            // on soustrait les jours feries ouvrables
            int nbJoursFerieOuvrables = jourFeriesOuvrables.size() - (debutEstFerie ? 1 : 0) - (finEstFerie ? 1 : 0);
            res = res.subtract(BigDecimal.valueOf(nbJoursFerieOuvrables));
        }

        return res;
    }

    /**
     * Calcule le nombre de jour ouvre entre deux dates, avec l'assumption qu'il y a 5 jours ouvres
     * par semaine. Le decompte est fait incluant les dates de debut et de fin. Est pris en compte
     * egalement si les dates de debut et de fin sont entieres ou à moitié selon le flag AM/PM du
     * Calendrier les representant. Si la date de debut est flagée comme AM, elle represente un
     * journée, si elle est flagés PM, elle represente une demi-journée. Idem mais dans l'autre sens
     * pour la date de fin : AM = une demi-journée, PM = 1 journée.
     *
     * @param debut la date de debut
     * @param fin   la date de fin
     * @return le nombre de jours ouvres calcules.
     */
    public static BigDecimal getDureeJoursOuvres(final Calendar debut, final Calendar fin) {

        int result = 0;

        int nbJours = getDureeJoursCalendairePleins(debut, fin);

        int nbWeeks = nbJours / SEPT;
        int remainder = nbJours % SEPT;

        if (remainder > 0) {
            int debutJour = debut.get(Calendar.DAY_OF_WEEK);
            int report = debutJour + remainder - 1;
            if (remainder > 1) {
                if (report > SEPT) { // si par dessus un dimanche
                    remainder = remainder - 1;
                }
                if (report > SIX) { // si par dessus un samedi
                    remainder = remainder - 1;
                }
            } else {
                if (report > SIX) { // si par dessus un samedi ou dimanche
                    remainder = remainder - 1;
                }
            }
            if (debutJour == Calendar.SUNDAY) {
                remainder = remainder - 1;
            }
        }

        result = nbWeeks * CINQ + remainder;

        BigDecimal res = new BigDecimal(result);

        if (result == 0) {
            return (res);
        }

        List<Calendar> jourFeriesOuvres = getJoursFeriesOuvres(debut, fin);
        boolean debutEstFerie = false;
        boolean finEstFerie = false;

        if (nbJours == 1) {

            int debutJour = debut.get(Calendar.DAY_OF_WEEK);
            // si la date de debut est ouvrable
            if (debutJour != Calendar.SUNDAY && debutJour != Calendar.SATURDAY) {
                Iterator<Calendar> iter = jourFeriesOuvres.iterator();
                // on recherche si la date de debut est feriée
                while (iter.hasNext()) {
                    if (compareToPrecisionJour(iter.next(), debut) == 0) {
                        debutEstFerie = true;
                    }
                }
                if (debutEstFerie) {
                    // si oui, on soustrait une journée
                    res = res.subtract(BigDecimal.ONE);
                } else {
                    // si, non, on regarde s'il faut soustraite une demie journée.
                    if ((debut.get(Calendar.AM_PM) == Calendar.AM && fin.get(Calendar.AM_PM) == Calendar.AM)
                            || (debut.get(Calendar.AM_PM) == Calendar.PM && fin.get(Calendar.AM_PM) == Calendar.PM)) {
                        res = res.subtract(BigDecimal.valueOf(CINQ, 1));
                    }
                }
            }

        } else {
            int debutJour = debut.get(Calendar.DAY_OF_WEEK);
            // si la date de debut est ouvrable
            if (debutJour != Calendar.SUNDAY && debutJour != Calendar.SATURDAY) {
                Iterator<Calendar> iter = jourFeriesOuvres.iterator();
                // on recherche si la date de debut est feriée
                while (iter.hasNext()) {
                    if (compareToPrecisionJour(iter.next(), debut) == 0) {
                        debutEstFerie = true;
                    }
                }
                if (debutEstFerie) {
                    // si oui, on soustrait une journée
                    res = res.subtract(BigDecimal.ONE);
                } else {
                    // si, non, on regarde s'il faut soustraite une demie journée.
                    if (debut.get(Calendar.AM_PM) == Calendar.PM) {
                        res = res.subtract(BigDecimal.valueOf(CINQ, 1));
                    }
                }
            }
            int finJour = fin.get(Calendar.DAY_OF_WEEK);
            // si la date de fin est ouvrable
            if (finJour != Calendar.SUNDAY && debutJour != Calendar.SATURDAY) {
                Iterator<Calendar> iter = jourFeriesOuvres.iterator();
                // on recherche si la date de fin est feriée
                while (iter.hasNext()) {
                    if (compareToPrecisionJour(iter.next(), fin) == 0) {
                        finEstFerie = true;
                    }
                }
                if (finEstFerie) {
                    // si oui, on soustrait une journée
                    res = res.subtract(BigDecimal.ONE);
                } else {
                    // si, non, on regarde s'il faut soustraite une demie journée.
                    if (fin.get(Calendar.AM_PM) == Calendar.AM) {
                        res = res.subtract(BigDecimal.valueOf(CINQ, 1));
                    }
                }
            }

            // on soustrait les jours feries ouvrables
            int nbJoursFerieOuvrables = jourFeriesOuvres.size() - (debutEstFerie ? 1 : 0) - (finEstFerie ? 1 : 0);
            res = res.subtract(BigDecimal.valueOf(nbJoursFerieOuvrables));
        }

        return res;
    }

    /**
     * Calcule une duree entre deux dates incluses en trentièmes sans tenir compte des flag AM et
     * PM.
     *
     * @param debut date de debut
     * @param fin   date de fin
     * @return la duree calculée en trentiemes
     */
    public static Duration getDureeTrentiemePlein(final Calendar debut, final Calendar fin) {

        Calendar debutClone = (Calendar) debut.clone();
        debutClone.set(Calendar.HOUR_OF_DAY, 1);

        Calendar finClone = (Calendar) fin.clone();
        finClone.set(Calendar.HOUR_OF_DAY, TREIZE);

        return getDureeTrentieme(debutClone, finClone);

    }

    /**
     * Calcule une duree entre deux dates incluses en trentièmes ... Est pris en compte egalement si
     * les dates de debut et de fin sont entieres ou à moitié selon le flag AM/PM du Calendrier les
     * representant. Si la date de debut est flagée comme AM, elle represente un journée, si elle
     * est flagés PM, elle represente une demi-journée. Idem mais dans l'autre sens pour la date de
     * fin : AM = une demi-journée, PM = 1 journée.
     *
     * @param debut date de debut
     * @param fin   date de fin
     * @return la duree calculée en trentiemes
     */
    public static Duration getDureeTrentieme(final Calendar debut, final Calendar fin) {

        Duration result = null;

        int reportFevrier1 = 0;
        int reportFevrier2 = 0;

        // test si meme jour sans tenir compte des flags AM ou PM
        boolean memeJour = (debut.get(Calendar.DAY_OF_YEAR) == fin.get(Calendar.DAY_OF_YEAR));

        // Si j2 est égal au 31, on ramène dat2 au 1er du mois suivant (j2 devient égal à 1 ; m2 = m2 + 1 si m2<12; m2=1 et a2 =
        // a2 +1 si m2 = 12).

        Calendar dat2 = (Calendar) debut.clone();
        if (dat2.get(Calendar.DAY_OF_MONTH) == TRENTE_ET_UN) {
            dat2.add(Calendar.DAY_OF_MONTH, 1);
            dat2.set(Calendar.HOUR_OF_DAY, 1);
        }

        // Si j1 est égal au 31, on ramène j1 au 30.
        Calendar dat1 = (Calendar) fin.clone();
        if (dat1.get(Calendar.DAY_OF_MONTH) == TRENTE_ET_UN) {
            dat1.add(Calendar.DAY_OF_MONTH, -1);
            dat1.set(Calendar.HOUR_OF_DAY, TREIZE);
        }

        // Si m1 = 2 (mois de février) :
        // - si a1 modulo 4 est égal à 0 et si j1 = 29 alors j1 est ramené au 30
        // - si a1 modulo 4 est différent de 0 et si j1 est égal à 28 alors j1 est ramené au 30.

        // si on est le meme jour, ne prends en comptre que le jour fin en compte pour le report de fevrier
        if (!memeJour) {
            if (dat2.get(Calendar.MONTH) == Calendar.FEBRUARY) {
                int modulo = dat2.get(Calendar.YEAR) % QUATRE;
                if (modulo == 0 && dat2.get(Calendar.DAY_OF_MONTH) == VINGT_NEUF) {
                    reportFevrier2 = 1;
                }
                if (modulo != 0 && dat2.get(Calendar.DAY_OF_MONTH) == VINGT_HUIT) {
                    reportFevrier2 = 2;
                }
            }
        }

        if (dat1.get(Calendar.MONTH) == Calendar.FEBRUARY) {
            int modulo = dat1.get(Calendar.YEAR) % QUATRE;
            if (modulo == 0 && dat1.get(Calendar.DAY_OF_MONTH) == VINGT_NEUF) {
                reportFevrier1 = 1;
            }
            if (modulo != 0 && dat1.get(Calendar.DAY_OF_MONTH) == VINGT_HUIT) {
                reportFevrier1 = 2;
            }
        }

        // On soustrait j2 à j1 ; m2 à m1 et a2 à a1 :

        int j = dat1.get(Calendar.DAY_OF_MONTH) - dat2.get(Calendar.DAY_OF_MONTH) + reportFevrier1 + 1;
        int m = dat1.get(Calendar.MONTH) - dat2.get(Calendar.MONTH);
        int a = dat1.get(Calendar.YEAR) - dat2.get(Calendar.YEAR);

        if (j < 0) {
            j += TRENTE;
            m -= 1;
        }
        if (m < 0) {
            m += DOUZE;
            a -= 1;
        }

        BigDecimal jour = new BigDecimal(j);
        if (memeJour) {
            if (dat2.get(Calendar.AM_PM) == dat1.get(Calendar.AM_PM)) {
                jour = jour.divide(BigDecimal.valueOf(DEUX));
            }
        } else {
            if (dat2.get(Calendar.AM_PM) == Calendar.PM) {
                if (reportFevrier2 > 0) {
                    jour = jour.subtract(BigDecimal.valueOf(reportFevrier2 + 1).divide(BigDecimal.valueOf(DEUX)));
                } else {
                    jour = jour.subtract(BigDecimal.valueOf(CINQ, 1));
                }
            }
            if (dat1.get(Calendar.AM_PM) == Calendar.AM) {
                if (reportFevrier1 > 0) {
                    jour = jour.subtract(BigDecimal.valueOf(reportFevrier1 + 1).divide(BigDecimal.valueOf(DEUX)));
                } else {
                    jour = jour.subtract(BigDecimal.valueOf(CINQ, 1));
                }
            }
        }

        // Si j1 est > 30 alors on soustrait 30 à j1 et on ajoute 1 à m1.
        if (jour.compareTo(BigDecimal.valueOf(TRENTE)) >= 0) {
            jour = jour.subtract(BigDecimal.valueOf(TRENTE));
            m += 1;
        }
        // Si m1 est > 12 alors on soustrait 12 à m1 et on ajoute 1 à a1.
        if (m >= DOUZE) {
            m -= DOUZE;
            a += 1;
        }

        result = new Duration(a, m, jour);

        return result;

    }

    /**
     * Calcule la liste des jours feriés entre deux dates incluses, qui sont ouvrable ( pas un
     * Dimanche).
     *
     * @param debutPeriode le debut de la periode des jours feriés.
     * @param finPeriode   a fin de la periode des jours feriés.
     * @return une liste de Calendriers.
     */
    public static List<Calendar> getJoursFeriesOuvrables(final Calendar debutPeriode, final Calendar finPeriode) {

        List<Calendar> jourFeriesOuvrables = new ArrayList<Calendar>();

        List<Calendar> jourFeries = getJoursFeries(debutPeriode, finPeriode);

        Iterator<Calendar> iter = jourFeries.iterator();
        while (iter.hasNext()) {
            Calendar date = iter.next();
            if (date.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                jourFeriesOuvrables.add(date);
            }
        }

        return jourFeriesOuvrables;
    }

    /**
     * Calcule la liste des jours feriés entre deux dates incluses, qui sont ouvrés ( pas un Samedi,
     * ni Dimanche).
     *
     * @param debutPeriode le debut de la periode des jours feriés.
     * @param finPeriode   a fin de la periode des jours feriés.
     * @return une liste de Calendriers.
     */
    public static List<Calendar> getJoursFeriesOuvres(final Calendar debutPeriode, final Calendar finPeriode) {

        List<Calendar> jourFeriesOuvres = new ArrayList<Calendar>();

        List<Calendar> jourFeries = getJoursFeries(debutPeriode, finPeriode);

        Iterator<Calendar> iter = jourFeries.iterator();
        while (iter.hasNext()) {
            Calendar date = iter.next();
            if (date.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && date.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                jourFeriesOuvres.add(date);
            }
        }

        return jourFeriesOuvres;
    }

    /**
     * Calcule la liste des jours feries compris entre deux dates incluses. Les jours feriés pris en
     * compte sont: jour de l'an, paques, fete du travail, 8 mai, ascension, pentecote, fete
     * nationale, assomption, toussaint, armistice, noel.
     *
     * @param debutPeriode le debut de la periode des jours feriés.
     * @param finPeriode   la fin de la periode des jours feriés.
     * @return une liste de Calendriers.
     */
    public static List<Calendar> getJoursFeries(final Calendar debutPeriode, final Calendar finPeriode) {

        List<Calendar> jourFeries = new ArrayList<Calendar>();

        Calendar debut = (Calendar) debutPeriode.clone();
        miseAZeroHeure(debut);
        debut.set(Calendar.AM_PM, Calendar.AM);

        Calendar fin = (Calendar) finPeriode.clone();
        miseAZeroHeure(fin);
        fin.set(Calendar.AM_PM, Calendar.AM);

        Calendar blankDate = Calendar.getInstance();
        miseAZeroHeure(blankDate);
        blankDate.set(Calendar.AM_PM, Calendar.AM);

        for (int annee = debut.get(Calendar.YEAR); annee <= fin.get(Calendar.YEAR); annee++) {

            // jour de l'an
            Calendar premJanvier = (Calendar) blankDate.clone();
            premJanvier.set(annee, Calendar.JANUARY, 1);
            if (premJanvier.compareTo(debut) >= 0 && premJanvier.compareTo(fin) <= 0) {
                jourFeries.add(premJanvier);
            }
            // lundi de Paques
            Calendar paques = getPaques(annee);
            miseAZeroHeure(paques);
            paques.set(Calendar.AM_PM, Calendar.AM);
            Calendar lundipaques = (Calendar) paques.clone();
            lundipaques.add(Calendar.DAY_OF_YEAR, 1);
            if (lundipaques.compareTo(debut) >= 0 && lundipaques.compareTo(fin) <= 0) {
                jourFeries.add(lundipaques);
            }
            // fete du traval
            Calendar premMai = (Calendar) blankDate.clone();
            premMai.set(annee, Calendar.MAY, 1);
            if (premMai.compareTo(debut) >= 0 && premMai.compareTo(fin) <= 0) {
                jourFeries.add(premMai);
            }
            // 8 mai
            Calendar huitMai = Calendar.getInstance();
            huitMai.set(annee, Calendar.MAY, HUIT);
            if (huitMai.compareTo(debut) >= 0 && huitMai.compareTo(fin) <= 0) {
                jourFeries.add(huitMai);
            }
            // Ascension
            Calendar ascension = (Calendar) paques.clone();
            ascension.add(Calendar.DAY_OF_MONTH, TRENTE_NEUF);
            if (ascension.compareTo(debut) >= 0 && ascension.compareTo(fin) <= 0) {
                jourFeries.add(ascension);
            }
            // Pentecote
            Calendar pentecote = (Calendar) paques.clone();
            pentecote.add(Calendar.DAY_OF_MONTH, CINQUANTE);
            if (pentecote.compareTo(debut) >= 0 && pentecote.compareTo(fin) <= 0) {
                jourFeries.add(pentecote);
            }
            // fete nationale
            Calendar quatorzeJuillet = (Calendar) blankDate.clone();
            quatorzeJuillet.set(annee, Calendar.JULY, QUATORZE);
            if (quatorzeJuillet.compareTo(debut) >= 0 && quatorzeJuillet.compareTo(fin) <= 0) {
                jourFeries.add(quatorzeJuillet);
            }
            // Assomption
            Calendar assumption = (Calendar) blankDate.clone();
            assumption.set(annee, Calendar.AUGUST, QUINZE);
            if (assumption.compareTo(debut) >= 0 && assumption.compareTo(fin) <= 0) {
                jourFeries.add(assumption);
            }
            // Toussaint
            Calendar toussaint = (Calendar) blankDate.clone();
            toussaint.set(annee, Calendar.NOVEMBER, 1);
            if (toussaint.compareTo(debut) >= 0 && toussaint.compareTo(fin) <= 0) {
                jourFeries.add(toussaint);
            }
            // Armistice
            Calendar onzeNovembre = (Calendar) blankDate.clone();
            onzeNovembre.set(annee, Calendar.NOVEMBER, ONZE);
            if (onzeNovembre.compareTo(debut) >= 0 && onzeNovembre.compareTo(fin) <= 0) {
                jourFeries.add(onzeNovembre);
            }
            // Noel
            Calendar noel = (Calendar) blankDate.clone();
            noel.set(annee, Calendar.DECEMBER, VINGT_CINQ);
            if (noel.compareTo(debut) >= 0 && noel.compareTo(fin) <= 0) {
                jourFeries.add(noel);
            }

        }

        return jourFeries;
    }

    /**
     * Calcule la date de paques pour une année donnée.
     *
     * Lilius et Clavius en 16xx. (Knuth vol1, p.155)
     *
     * @param annee l'anne pour laquelle paques est a calculer.
     * @return un Calendrier representant la date de paques.
     */
    public static Calendar getPaques(final int annee) {

        Calendar paques = Calendar.getInstance();
        paques.set(Calendar.YEAR, annee);

        // [Golden number]. G = (Y mod 19) + 1
        int g = (annee % DIX_NEUF) + 1;
        // [Century] C = ë Y/100 û + 1
        int c = annee / CENT + 1;
        // [Corrections] X = ë 3 C / 4 û - 12, Z = ë (8 C + 5) /25 û -5
        int x = TROIS * c / QUATRE - DOUZE;
        int z = (HUIT * c + CINQ) / VINGT_CINQ - CINQ;
        // [Find Sunday] D = ë 5 Y / 4 û - X -10
        int d = CINQ * annee / QUATRE - x - DIX;
        // [Epact.] E = (11 G + 20 + Z - X) mod 30. Si E = 25 and G > 11, ou si E = 24, alors E ¬ E + 1
        int e = (ONZE * g + VINGT + z - x) % TRENTE;
        if (e == VINGT_CINQ && g > ONZE || e == VINGT_QUATRE) {
            ++e;
        }
        // [Find full moon] N = 44 - E. Si N < 21, alors N ¬ N + 30
        int n = QUARANTE_QUATRE - e;
        if (n < VINGT_ET_UN) {
            n = n + TRENTE;
        }
        // [Advance to Sunday] N ¬ N + 7 - ((D + N) mod 7)
        int j = n + SEPT - ((d + n) % SEPT);
        // [Get month] If N > 31, date is (N - 31) APRIL Otherwise, date is N MARCH.
        if (j > TRENTE_ET_UN) {
            paques.set(Calendar.MONTH, Calendar.APRIL);
            paques.set(Calendar.DAY_OF_MONTH, j - TRENTE_ET_UN);
        } else {
            paques.set(Calendar.MONTH, Calendar.MARCH);
            paques.set(Calendar.DAY_OF_MONTH, j);
        }
        return paques;
    }

}
