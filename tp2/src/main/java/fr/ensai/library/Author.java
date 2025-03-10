package fr.ensai.library;

import java.util.Objects;

/**
 * Represents an Author.
 */
public class Author {

    // Attributes
    private String name;
    private int age;
    private String nationality;

    /**
     * Constructs a new Book object.
     */
    public Author(String name) {
        this.name = name;
        this.age = 0;
        this.nationality = "unknown";
    }

    public String getName() {
        return this.name;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * Two authors are considered equal if their names are equal.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Author author = (Author) obj;
        return Objects.equals(name, author.name);
    }

    @Override
    public String toString() {
        return "Author " + name;
    }

}
