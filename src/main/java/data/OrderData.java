package data;

/**
 * @author Sharapov Yuri
 */
public enum OrderData {
    ORDER_NAME("//input[@id='name']", "Ivan"),
    ORDER_COUNTRY("//input[@id='country']", "Russia"),
    ORDER_CITY("//input[@id='city']", "Moscow"),
    ORDER_CARD("//input[@id='card']", "2200114455557777"),
    ORDER_MONTH("//input[@id='month']", "12"),
    ORDER_YEAR("//input[@id='year']", "2029");

    private final String xpath;
    private final String data;


    OrderData(String xpath, String data) {
        this.xpath = xpath;
        this.data = data;
    }

    public String getXpath() {
        return xpath;
    }

    public String getData() {
        return data;
    }
}
