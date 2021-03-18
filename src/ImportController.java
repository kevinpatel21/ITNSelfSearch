public class ImportController {
    private DatabaseImport importingDatabase;
    private Database newDatabase;

    public ImportController(String filePath) {
        importingDatabase = new DatabaseImport(filePath);
    }
    Database overrideDatabase(String defaultPassword){
        newDatabase = new Database(defaultPassword, importingDatabase);
        return newDatabase;
    }
}
