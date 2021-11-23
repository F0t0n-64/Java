package pjv.alsa.cv01.entity;
import pjv.alsa.cv01.entity.part.ComputerPart;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class Notebook extends Product implements Serializable {

    private final NotebookCategory notebookCategory;
    private final ComputerPart[]   computerParts;

    public Notebook(int id, String name, double price, int count, NotebookCategory notebookCategory, ComputerPart[] computerParts) {
        super(id, name, price, count);
        this.notebookCategory = notebookCategory;
        this.computerParts    = computerParts;
    }

    public NotebookCategory getNotebookCategory() {
        return notebookCategory;
    }

    public ComputerPart[] getComputerParts() {
        return computerParts;
    }

    @Override
    public Product withIncreasedCount() {
        return new Notebook(id, name, price, count + 1, notebookCategory, computerParts);
    }

    @Override
    public Product withDecreasedCount() {
        return new Notebook(id, name, price, count - 1, notebookCategory, computerParts);
    }

    @Override
    public boolean hasSpecialGuarantee() {
        return true;
    }

    @Override
    public String toString() {
        return "Notebook{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", notebookCategory=" + notebookCategory +
                ", computerParts=" + Arrays.toString(computerParts) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Notebook notebook = (Notebook) o;
        return notebookCategory == notebook.notebookCategory && Arrays.equals(computerParts, notebook.computerParts);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(super.hashCode(), notebookCategory);
        result = 31 * result + Arrays.hashCode(computerParts);
        return result;
    }
}
