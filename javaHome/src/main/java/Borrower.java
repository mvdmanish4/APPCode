/**
 * @program: library management system
 * @description: This is the Borrower Class
 * @author: Xinyong
 * @create: 2019-09-14 01:01
 **/
public class Borrower {
    public String borrower_id = null; //This is the unique alphanumeric identifier for each borrower
    public String borrower_phone = null;
    public String borrower_name = null;

    public String getBorrower_phone() {
        return borrower_phone;
    }

    public void setBorrower_phone(String borrower_phone) {
        this.borrower_phone = borrower_phone;
    }

    public String getBorrower_name() {
        return borrower_name;
    }

    public void setBorrower_name(String borrower_name) {
        this.borrower_name = borrower_name;
    }

    public String getBorrower_id() {
        return borrower_id;
    }

    public void setBorrower_id(String borrower_id) {
        this.borrower_id = borrower_id;
    }
}
