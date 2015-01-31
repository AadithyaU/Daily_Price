package com.androidbegin.menuviewpagertutorial;
import android.os.Parcel;
import android.os.Parcelable;
import android.graphics.drawable.Drawable;
public class WorldPopulation implements Parcelable{
	public String Price;
	 public String Product;
	 public String Picture;
	 public String Category;
	public boolean selected;
	public WorldPopulation()
    {
        
    }
	public WorldPopulation(Parcel in)
    {
        readFromParcel(in);
    }
	@Override
    public int describeContents()
    {
        return 0;
    }
	public void writeToParcel(Parcel dest, int flags)
    {
        // We just need to write each field into the
        // parcel. When we read from parcel, they
        // will come back in the same order
		dest.writeString(this.getPrice());
        dest.writeString(this.getProduct());
        dest.writeString(this.getPicture());  
        //dest.writeInt(this.intValue);
    }
	public void readFromParcel(Parcel in)
    {
        // We just need to read back each
        // field in the order that it was
        // written to the parcel

        this.setPrice( in.readString());
        this.setProduct( in.readString());
        this.setPicture( in.readString());
    }
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator()
    {
        @Override
        public WorldPopulation createFromParcel(Parcel in)
        {
            return new WorldPopulation(in);
        }

        @Override
        public Object[] newArray(int size)
        {
            return new WorldPopulation[size];
        }
    };
	public String getPrice() {
		return Price;
	}
	
	public void setPrice(String Price) {
		this.Price = Price;
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String Category) {
		this.Category = Category;
	}
	
	public String getProduct() {
		return Product;
	}

	public void setProduct(String Product) {
		this.Product = Product;
	}

	public String getPicture() {
		return Picture;
	}

	public void setPicture(String Picture) {
		this.Picture = Picture;
	}
}