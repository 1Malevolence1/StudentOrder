package domain;

public class PassportOffice extends Office {

   private Long officeId;
   private String officeAreaId;
   private String officeName;

   public PassportOffice(Long officeId, String officeAreaId, String officeName) {
      super(officeId, officeAreaId, officeName);
   }

   public PassportOffice() {
   }
}
