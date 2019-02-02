package comp3350.breadtunes.application;

import comp3350.breadtunes.persistence.SongPersistence;
import comp3350.breadtunes.persistence.SCPersistence;
import comp3350.breadtunes.persistence.StudentPersistence;
import comp3350.breadtunes.persistence.stubs.SongPersistenceStub;
import comp3350.breadtunes.persistence.stubs.SCPersistenceStub;
import comp3350.breadtunes.persistence.stubs.StudentPersistenceStub;

public class Services
{
	private static StudentPersistence studentPersistence = null;
	private static SongPersistence songPersistence = null;
	private static SCPersistence scPersistence = null;

	public static synchronized StudentPersistence getStudentPersistence()
    {
		if (studentPersistence == null)
		{
		    studentPersistence = new StudentPersistenceStub();
        }

        return studentPersistence;
	}

    public static synchronized SongPersistence getSongPersistence()
    {
        if (songPersistence == null)
        {
            songPersistence = new SongPersistenceStub();
        }

        return songPersistence;
    }

	public static synchronized SCPersistence getScPersistence() {
        if (scPersistence == null) {
            scPersistence = new SCPersistenceStub();
        }

        return scPersistence;
    }
}
