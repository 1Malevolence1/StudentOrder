package domain;

public enum StudentOrderStatus {
    START, CHECKED;

    public static StudentOrderStatus fromValue(int value){
        for (StudentOrderStatus sos : values()){
            if(sos.ordinal() == value){
                return sos;
            }
        }
        throw new RuntimeException("Unknown value" + value);
    }
}
