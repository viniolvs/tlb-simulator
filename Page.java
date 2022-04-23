/**
 * Page
 */
public class Page {
    private String p; //page number 
    private String d; //offset
    
    public Page(){};
    public Page(Page page) {
        this.d = page.getD();
        this.p = page.getP();
    }
    public Page(String p, String d){
        this.p = p;
        this.d = d;
    }
    public String getP() {
        return p;
    }
    public String getD() {
        return d;   
    }
}