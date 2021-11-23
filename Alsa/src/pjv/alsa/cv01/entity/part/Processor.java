package pjv.alsa.cv01.entity.part;

import java.io.Serializable;
import java.util.Objects;

public class Processor extends ComputerPart implements Serializable {

    private final ProcessorSpeed speed;

    public Processor(ProcessorSpeed speed) {
        this.speed = speed;
    }

    public ProcessorSpeed getSpeed() {
        return speed;
    }

    @Override
    public String toString() {
        return "Processor{" +
                "speed=" + speed +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Processor processor = (Processor) o;
        return speed == processor.speed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(speed);
    }
}
