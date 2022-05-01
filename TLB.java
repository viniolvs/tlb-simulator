/**
 * TLB
 * O tempo de acceso da tlb considerado de 4ns.
 */

import java.util.LinkedList;

public class TLB {

    private int size;
    private LinkedList<Page> tlbPages;
    private int nPages;

    public TLB(int size){
        tlbPages = new LinkedList<>();
        this.size = size;
        nPages = 0;
    }

    public void addPageFIFO(Page page) {
        //tlb não está cheia
        if(nPages < size){
            tlbPages.addFirst(page);
            nPages++;
        }
        //tlb está cheia
        else{
            //página a ser adicionada não está na lista, remove do fim e adiciona nova page no começo
            int index = containsPage(page);
            if(index == -1){
                tlbPages.removeLast();
                tlbPages.addFirst(page);
            }
            //pagina ja esta na tlb, reposiciona a pagina para o começo da lista
            else{
                tlbPages.remove(index);
                tlbPages.addFirst(page);
            }
        }
    }

    //returna posição da pagina na lista caso exista, ou -1 casi não exista 
    public int containsPage(Page page) {
        int index=0;
        for (Page p : tlbPages) {
            if (p.getP().intern() == page.getP().intern())
                return index;
            index++;
        }
        return -1;
    }
    //return the position on tlb
    public LinkedList<Page> getTLB() {
        return tlbPages;
    }

    public int getSize() {
        return size;
    }

    public static double getAccessTime() {
        return 4;
    }

    public void printTLB() {
       for (Page page : tlbPages) {
           System.out.println("\nPages on TLB = "+page.getP()+" ");
       }
    }
}