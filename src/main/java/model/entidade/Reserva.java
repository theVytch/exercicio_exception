package model.entidade;

import model.exceptions.DomainException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reserva {

    private Integer numeroQuarto;
    private Date checkIn;
    private Date checkout;

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public Reserva(Integer numeroQuarto, Date checkIn, Date checkout) throws DomainException{
    if (!checkout.after(checkIn)){
        throw new DomainException("data de checkou precisa ser depois da data de checkin ");
    }
        this.numeroQuarto = numeroQuarto;
        this.checkIn = checkIn;
        this.checkout = checkout;
    }

    public Integer getNumeroQuarto() {
        return numeroQuarto;
    }

    public void setNumeroQuarto(Integer numeroQuarto) {
        this.numeroQuarto = numeroQuarto;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckout() {
        return checkout;
    }

    public long duracao(){
        long diff = checkout.getTime() - checkIn.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public void atualizaData(Date checkIn, Date checkout) throws DomainException{
        Date now = new Date();
        if (checkIn.before(now) || checkout.before(now)){
            throw new DomainException("checkin e checkout precisa ser datas futuras");
        }if (!checkout.after(checkIn)){
            throw new DomainException("data de checkou precisa ser depois da data de checkin ");
        }
        this.checkIn = checkIn;
        this.checkout = checkout;
    }

    @Override
    public String toString() {
        return "Quarto " +
                 numeroQuarto +
                ", checkIn=" + sdf.format(checkIn) +
                ", checkout=" + sdf.format(checkout) +
                ", "+
                duracao()+
                " noites ";
    }
}
