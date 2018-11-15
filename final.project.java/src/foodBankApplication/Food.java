/*
 * This work is open under no license.
 */
package foodBankApplication;

/**
 * <b>OVERVIEW:</b> Describes a single Food object; mutable
 *
 * <b>Abstraction Function:</b>
 *             A food item is represented by its name, country of origin,
 *             if it's refrigerated and its point value. Example:
 *             Food item: bananas, Origin: Russia, Refrigerated: true, 
 *             Point value: 1
 * 
 * <b>Rep Invariant</b>: 
 *      c.name is a String &&
 *      c.countryOfOrigin is a String &&
 *      c.isRefrigerated is a boolean &&
 *      c.pointValue is an Integer &&
 *      c.pointValue is in the interval [0,10)
 * 
 * @author thepromisedLAN, luiszugasti, yashbhatty
 */
public class Food {
    private String name;
    private String countryOfOrigin;
    private boolean isRefrigerated;
    private int pointValue;
    
    /**
     * <b>OVERVIEW:</b> Constructor for the Food object
     * 
     * <b>REQUIRES:</b> -
     * <b>MODIFIES:</b> name:String, countryOfOrigin:String
     * <b>EFFECTS:</b> Initializes instances 'name' and 'countryOfOrigin';
     *          Sets 'pointValue' to 0
     * @param name
     * @param countryOfOrigin 
     */
    public Food(String name, String countryOfOrigin){
        this.name=name;
        this.countryOfOrigin=countryOfOrigin;
        setPointValue(0);
    }

    /**
     * <b>OVERVIEW:</b> Accessor for the 'name' instance
     * <b>EFFECTS:</b> Returns 'name'
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * OVERVIEW:</b> Modifier for the 'name' instance
     * EFFECTS:</b> Sets 'name' to the value provided
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * <b>OVERVIEW:</b> Accessor for the 'countryOfOrigin' instance
     * <b>EFFECTS:</b> Returns 'countryOfOrigin'
     * @return countryOfOrigin
     */
    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    /**
     * <b>OVERVIEW:</b> Modifier for the 'countryOfOrigin' instance
     * <b>EFFECTS:</b> Sets 'countryOfOrigin' to the value provided
     * @param countryOfOrigin
     */
    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    /**
     * <b>OVERVIEW:</b> Accessor for the 'isRefrigerated' instance
     * <b>EFFECTS:</b> Returns 'isRefrigerated'
     * @return isRefrigerated
     */
    public boolean isIsRefrigerated() {
        return isRefrigerated;
    }

    /**
     * <b>OVERVIEW:</b> Modifier for the 'isRefrigerated' instance
     * <b>EFFECTS:</b> Sets 'isRefrigerated' to the value provided
     * @param isRefrigerated
     */
    public void setIsRefrigerated(boolean isRefrigerated) {
        this.isRefrigerated = isRefrigerated;
    }

    /**
     * <b>OVERVIEW:</b> Accessor for the 'pointValue' instance
     * <b>EFFECTS:</b> Returns 'pointValue'
     * @return pointValue
     */
    public int getPointValue() {
        return pointValue;
    }

    /**
     * <b>OVERVIEW:</b> Modifier for the 'countryOfOrigin' instance
     * <b>EFFECTS:</b> Sets 'countryOfOrigin' to the value provided
     * @param pointValue
     */
    public void setPointValue(int pointValue) {
        this.pointValue = pointValue;
    }
    
    /**
     * <b>OVERVIEW:</b> Implements the rep invariants of Food; provides
     *                  description of the object
     * <b>EFFECTS:</b> Returns object description in a String
     * @return String
     */
    public String toString() {
        return "Food item: " + name +", Origin: " + countryOfOrigin + ""
                + ", Refrigerated: " + isRefrigerated + ", Point value: "
                + pointValue;
    }
    
    public boolean repOK(){
        if (pointValue<0&&pointValue>=10){
            return false;
        }
        else {
            return (pointValue==(int)pointValue && name ==(String)name && (isRefrigerated==true||isRefrigerated==false) && countryOfOrigin == (String)countryOfOrigin);
        }
    }
}
