//Revision 1.0 (Pendiente Revision Final)
package classy_code.model.report;

// Library Imports
import java.util.ArrayList;
import java.util.GregorianCalendar;

// Local Imports
import classy_code.model.menu.Product;
import classy_code.model.order.ChairOrder;
import classy_code.model.order.TableOrder;

/*
 * clase ReportWarehouse
 */
public class ReportWarehouse {
    // Atributos
    private ArrayList<Report> month_report_list;
    private ArrayList<Report> week_report_list;
    private ArrayList<Report> day_report_list;
    @SuppressWarnings("unused")
    private SaleWarehouse saleWarehouse;
    private ArrayList<Sale> sale_list;
    
    // Constructor
    /*
     * Constructor de la clase ReportWarehouse
     * @param saleWarehouse
     */
    public ReportWarehouse(SaleWarehouse saleWarehouse) {
        this.month_report_list = new ArrayList<Report>();
        this.week_report_list = new ArrayList<Report>();
        this.day_report_list = new ArrayList<Report>();
        this.saleWarehouse = saleWarehouse;
        this.sale_list = saleWarehouse.getSale_list();
    }

    // Metodos
    /*
     * Metodo isSameDay
     * Devuelve si dos fechas ocurren el mismo dia
     * @param date1
     * @param date2
     */
    public static boolean isSameDay(GregorianCalendar date1, GregorianCalendar date2) {
        return date1.get(GregorianCalendar.YEAR) == date2.get(GregorianCalendar.YEAR)
                && date1.get(GregorianCalendar.MONTH) == date2.get(GregorianCalendar.MONTH)
                && date1.get(GregorianCalendar.DAY_OF_MONTH) == date2.get(GregorianCalendar.DAY_OF_MONTH);
    }

    /*
     * Metodo isSameWeek
     * Devuelve si dos fechas ocurren la misma semana
     * @param date1
     * @param date2
     */
    public static boolean isSameWeek(GregorianCalendar date1, GregorianCalendar date2) {
        return date1.get(GregorianCalendar.YEAR) == date2.get(GregorianCalendar.YEAR)
                && date1.get(GregorianCalendar.WEEK_OF_YEAR) == date2.get(GregorianCalendar.WEEK_OF_YEAR);
    }

    /*
     * Metodo isSameMonth
     * Devuelve si dos fechas ocurren el mismo mes
     * @param date1
     * @param date2
     */
    public static boolean isSameMonth(GregorianCalendar date1, GregorianCalendar date2) {
        return date1.get(GregorianCalendar.YEAR) == date2.get(GregorianCalendar.YEAR)
                && date1.get(GregorianCalendar.MONTH) == date2.get(GregorianCalendar.MONTH);
    }

    /*
     * Metodo generateMonthReport
     * Genera un reporte mensual
     */
    public void generateMonthReport() {
        GregorianCalendar today = new GregorianCalendar();
        ArrayList<Product> product_list = new ArrayList<Product>();
        for (Sale sale : sale_list) {
            if (isSameMonth(today, sale.getDate())) {
                TableOrder order = sale.getOrder();
                for (ChairOrder chair_order : order.getOrder_list()) {
                    product_list.addAll(chair_order.getDrinkList());
                    product_list.addAll(chair_order.getFoodList());
                }
            }
        }
        month_report_list.add(new Report(month_report_list.size() + 1, product_list));
    }

    /*
     * Metodo generateWeekReport
     * Genera un reporte semanal
     */
    public void generateWeekReport() {
        GregorianCalendar today = new GregorianCalendar();
        ArrayList<Product> product_list = new ArrayList<Product>();
        for (Sale sale : sale_list) {
            if (isSameWeek(today, sale.getDate())) {
                TableOrder order = sale.getOrder();
                for (ChairOrder chair_order : order.getOrder_list()) {
                    product_list.addAll(chair_order.getDrinkList());
                    product_list.addAll(chair_order.getFoodList());
                }
            }
        }
        week_report_list.add(new Report(week_report_list.size() + 1, product_list));
    }

    /*
     * Metodo generateDayReport
     * Genera un reporte diario
     */
    public void generateDayReport() {
        GregorianCalendar today = new GregorianCalendar();
        ArrayList<Product> product_list = new ArrayList<Product>();
        for (Sale sale : sale_list) {
            if (isSameDay(today, sale.getDate())) {
                TableOrder order = sale.getOrder();
                for (ChairOrder chair_order : order.getOrder_list()) {
                    product_list.addAll(chair_order.getDrinkList());
                    product_list.addAll(chair_order.getFoodList());
                }
            }
        }
        day_report_list.add(new Report(day_report_list.size() + 1, product_list));
    }
}
