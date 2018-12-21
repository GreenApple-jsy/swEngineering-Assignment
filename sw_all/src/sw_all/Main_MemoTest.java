package sw_all;

import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import org.junit.jupiter.api.Test;

class Main_MemoTest {
	
	@Test
	void testmemocreate() {
		Main_Memo memo = new Main_Memo();
		String title = "testmemo";
		assertTrue(memo.createMemo(title));
        }

	@Test
	void testDelete() {
		Main_Memo memo = new Main_Memo();
		String title = "testmemo";
		memo.createMemo(title);
        assertTrue(memo.Deletecheck(title));
	}
}