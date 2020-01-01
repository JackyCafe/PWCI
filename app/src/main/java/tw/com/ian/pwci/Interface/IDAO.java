package tw.com.ian.pwci.Interface;
import android.content.Context;
import android.database.Cursor;

import java.util.List;

public abstract class IDAO<T> extends DAO {
    public IDAO(Context context) {
        super(context);
    }

    public abstract T insert(T t);
    public abstract boolean delete(long id);
    public abstract T update(T t);
    public abstract List<T> getAll();
    protected abstract T getRecord(Cursor c);
}
