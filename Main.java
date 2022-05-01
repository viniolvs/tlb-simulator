/**
 * Main
 * Lê os traces de um arquivo e grava as paginas na TLB.
 * Para cada novo trace lido, verifica se a pagina esta na TLB,
 * se não estiver, gera um tlb miss e substitui uma pagina da TLB pela nova pagina.
 */

 /*
 * 32 bit address
 * page size 4096 bytes = 2^12
 * logical address = 2^32
 * n = 12
 * m = 32
 * p = 32 - 12 = 20 bits (page number)
 * d = n = 12 = 12 bits (offset)
 * trace format = 07b243a0 R
 * p = trace first 5 hex, d = trace last 3 hex
 */

import java.lang.ArrayIndexOutOfBoundsException;

public class Main {
    public static void main(String[] args) {
        try {
            PageTable PT = new PageTable(args[0]);
            TLB tlb = new TLB(Integer.parseInt(args[1]));
            int tlb_miss = 0;
            int entries = 0;

            Page page;
            Pair pair;

            long begin = System.currentTimeMillis();
            while(true){
                pair = PT.getNextPage();
                page = pair.getPage();
                /*System.out.println("\nNúmero da pagina = "+page.getP());
                System.out.print("TLB = "); tlb.printTLB();*/

                if(pair.getBool() == false)
                    break;

                entries++;
                if (tlb.containsPage(page) == -1)
                    tlb_miss++;
                
                tlb.addPageFIFO(page);
                
            }
            long end = System.currentTimeMillis();

            double tlb_hit_rate = ((entries-tlb_miss)*100)/entries;

            System.out.println("Entradas = " + entries +" Miss = " +tlb_miss);

            System.out.println("Taxa de acerto TLB com tamanho "+tlb.getSize()+" = "+tlb_hit_rate+"%");

            System.out.println("Tempo de execução = "+ (end-begin) + " milisegundos.");

            System.out.println("Tempo de acesso memória = "+PageTable.getAccessTime() + " nanosegundos.");
            
            System.out.println("Tempo de acesso TLB = "+TLB.getAccessTime() + " nanosegundos.");

            double total_memTime = tlb_miss * PageTable.getAccessTime();
            double total_TLBTime = (entries-tlb_miss) * TLB.getAccessTime();

            System.out.println("Tempo gasto acessando a memória = (Miss * MemoryAccessTime) = " + total_memTime + " nanosegundos");
            
            System.out.println("Tempo gasto acessando a TLB = ((Entradas - Miss) * TLBAccessTime) = " + total_TLBTime + " nanosegundos");

            System.out.println("Tempo total de execução (estimado) = " + (total_TLBTime + total_memTime) + " nanosegundos");
            
        } catch (ArrayIndexOutOfBoundsException e){
            System.err.println("format: java Main <trace filename(String)> <TLB size(int)>");
        }
    }
}