package de.atruvia.webapp.domain.model;

import java.util.UUID;

public class Person {

    private UUID id;


    private String vorname;


    private String nachname;

    public Person(UUID id, String vorname, String nachname) {
        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
    }

    public Person() {
    }

    public static PersonBuilder builder() {
        return new PersonBuilder();
    }

    public UUID getId() {
        return this.id;
    }

    public String getVorname() {
        return this.vorname;
    }

    public String getNachname() {
        return this.nachname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Person)) return false;
        final Person other = (Person) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$vorname = this.getVorname();
        final Object other$vorname = other.getVorname();
        if (this$vorname == null ? other$vorname != null : !this$vorname.equals(other$vorname)) return false;
        final Object this$nachname = this.getNachname();
        final Object other$nachname = other.getNachname();
        if (this$nachname == null ? other$nachname != null : !this$nachname.equals(other$nachname)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Person;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $vorname = this.getVorname();
        result = result * PRIME + ($vorname == null ? 43 : $vorname.hashCode());
        final Object $nachname = this.getNachname();
        result = result * PRIME + ($nachname == null ? 43 : $nachname.hashCode());
        return result;
    }

    public String toString() {
        return "Person(id=" + this.getId() + ", vorname=" + this.getVorname() + ", nachname=" + this.getNachname() + ")";
    }

    private void setId(UUID id) {
        this.id = id;
    }

    public static class PersonBuilder {
        private UUID id;
        private String vorname;
        private String nachname;

        PersonBuilder() {
        }

        public PersonBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public PersonBuilder vorname(String vorname) {
            this.vorname = vorname;
            return this;
        }

        public PersonBuilder nachname(String nachname) {
            this.nachname = nachname;
            return this;
        }

        public Person build() {
            return new Person(this.id, this.vorname, this.nachname);
        }

        public String toString() {
            return "Person.PersonBuilder(id=" + this.id + ", vorname=" + this.vorname + ", nachname=" + this.nachname + ")";
        }
    }
}
