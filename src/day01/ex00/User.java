package ex00;

import java.util.UUID;

public class User {
   private int uid;
   private String name;
   private long balance;


   User (int uid, String name, long balance ) {
      if (uid <= 0) {
         System.err.println("IllegalArgument uid");
         System.exit(-1);
      }
      this.uid = uid;
      this.name = name;
      this.balance = balance;
   }

   public void setUid(int uid) {
      if (uid <= 0) {
         System.err.println("IllegalArgument uid");
         System.exit(-1);
      }
         this.uid = uid;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setBalance(long balance) {
      if (balance < 0) {
         System.err.println("IllegalArgument balance");
         System.exit(-1);
      }
      this.balance = balance;
   }

   public int getUid() {
      return uid;
   }

   public String getName() {
      return name;
   }

   public long getBalance() {
      return balance;
   }
}
