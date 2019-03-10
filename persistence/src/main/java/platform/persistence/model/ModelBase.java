package platform.persistence.model;

import com.sun.javafx.beans.IDProperty;
import org.springframework.data.annotation.Id;

/**
 * Created by admin on 2019/2/17.
 */
public class ModelBase
{
    @Id
    private String id;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }
}
