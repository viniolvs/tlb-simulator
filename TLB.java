/**
 * TLB
 */
public class TLB {

    private int size;
    private Page[] tlbPages;
    private int tlb_idx;
    private int size_idx;

    public TLB(int size){
        this.size = size;
        tlbPages = new Page[size];
        tlb_idx = 0;
        size_idx = 0;
    }

    public void addPageFIFO(Page page) {
        if(tlb_idx < size){
            tlbPages[tlb_idx++] = page;
            if(size_idx < size)
                size_idx++;
        }
        else{
            tlb_idx = 0;
            tlbPages[tlb_idx++] = page;
        }
    }

    public boolean searchPage(Page page) {
        for (int i = 0 ; i < size_idx ; i++) {
            if(tlbPages[i].getP().intern() == page.getP().intern())
                return true;
        }
        return false;
    }

    public Page[] getTLB() {
        return tlbPages;
    }

    public int getSize() {
        return size;
    }

    public void printTLB() {
        for (int i = 0; i < size_idx; i++) {
            System.out.print(tlbPages[i].getP()+" ");
        }
    }
}