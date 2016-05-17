package Model;

import java.io.Serializable;

public interface LockTableArray extends Serializable {

		int Len();

		void update(int id, int value);

		int Search(int id);

		String ToString();

}
