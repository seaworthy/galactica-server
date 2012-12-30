public class Protocol {
    public String process(String string) {
	long time = System.currentTimeMillis();
	return String.valueOf(time) + " " + string;
    }
}
