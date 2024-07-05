package nikitaivanov.gestione_eventi.evento;

import jakarta.persistence.*;
import jdk.jfr.Event;
import nikitaivanov.gestione_eventi.user.User;

import java.time.LocalDate;
@Entity
@Table(name = "eventi")
public class Evento {
    //Attributi
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String titolo;
    private String descrizione;
    private LocalDate data;
    private String luogo;
    private int posti;

    //many to one
    @ManyToOne
    @JoinColumn(name = "organizzatore", nullable = false)
    private User user;

    //Costruttori
    public Evento(){}

    public Evento(String titolo, String descrizione, LocalDate data, String luogo, int posti, User organizzatore) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.data = data;
        this.luogo = luogo;
        this.posti = posti;
        this.user = organizzatore;
    }
    //Metodi


    public long getId() {
        return id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getLuogo() {
        return luogo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public int getPosti() {
        return posti;
    }

    public void setPosti(int posti) {
        this.posti = posti;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "id=" + id +
                ", titolo='" + titolo + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", data=" + data +
                ", luogo='" + luogo + '\'' +
                ", posti=" + posti +
                ", user=" + user +
                '}';
    }
}
