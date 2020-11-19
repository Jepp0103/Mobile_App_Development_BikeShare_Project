package dk.itu.nchh.bikeshare;

public class Ride {
    private String mBikeName ;
    private String mStartRide ;
    private String mEndRide;

    public Ride ( String bikeName , String startRide, String endRide ) {
        mBikeName = bikeName ;
        mStartRide = startRide ;
        mEndRide = endRide;

    }

    public String getBikeName () {
        return mBikeName ;
    }

    public void setBikeName ( String bikeName ) {
        mBikeName = bikeName ;
    }

    public String getStartRide () {
        return mStartRide ;
    }

    public void setStartRide ( String startRide ) {
        mStartRide = startRide ;
    }


    public String getEndRide() {
        return mEndRide;
    }

    public void setEndRide(String mEndRide) {
        this.mEndRide = mEndRide;
    }

    public String toString () {
        return mBikeName + " started here : " + mStartRide + " ended at : " + mEndRide ;
    }




}
