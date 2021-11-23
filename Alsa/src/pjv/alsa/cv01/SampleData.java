package pjv.alsa.cv01;
import pjv.alsa.cv01.entity.Notebook;
import pjv.alsa.cv01.entity.NotebookCategory;
import pjv.alsa.cv01.entity.Television;
import pjv.alsa.cv01.entity.part.ComputerPart;
import pjv.alsa.cv01.entity.part.Memory;
import pjv.alsa.cv01.entity.part.Processor;
import pjv.alsa.cv01.entity.part.ProcessorSpeed;

public class SampleData {

    public final static Processor intel = new Processor(ProcessorSpeed.FAST);
    public final static Processor amd   = new Processor(ProcessorSpeed.FAST);

    public final static Memory memory200GB = new Memory(200);
    public final static Memory memory500GB = new Memory(500);

    public final static Notebook lenovoE500     = new Notebook(1,"Lenovo E500", 10000, 2, NotebookCategory.BASIC, new ComputerPart[]{intel, memory200GB});
    public final static Notebook hpBusinnesPlus = new Notebook(2,"HP Business Plus", 40000, 5, NotebookCategory.PROFESSIONAL, new ComputerPart[]{amd, memory500GB});

    public final static Television samsungMediaPlus = new Television(3,"Samsung Media Plus", 5000, 2);

}
