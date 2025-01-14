package obsidianAPI.file;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import obsidianAPI.exceptions.MissingImporterException;
import obsidianAPI.file.importer.FileLoader;
import obsidianAPI.file.importer.ImporterTabula;
import obsidianAPI.file.importer.ModelImporter;
import obsidianAPI.render.ModelObj;

public class ModelFileHandler {

	public static final ModelFileHandler instance = new ModelFileHandler();
	private final Map<String, ModelImporter> importers = new HashMap<String, ModelImporter>();

	public static final String MODEL_NAME = "model.obj";
	public static final String TEXTURE_NAME = "texture.png";
	public static final String SETUP_NAME = "setup.dat";

	public ModelFileHandler() {
		importers.put(FileHandler.tabulaModelExtension, ImporterTabula.instance);
	}

	public <T extends ModelObj> T importModel(File file, Class<T> clazz) throws MissingImporterException {
		String fileName = file.getName();
		String extension = fileName.substring(fileName.indexOf(".") + 1, fileName.length());
		
		//If importing from obsidian file, just load
		if(extension.equals("obm")) {
			FileHandler.copyFileToPersistentMemory(file);
			return FileLoader.fromFile(file, clazz);
		}

		//If importing from other file type, import to obm file and
		// then load from that file. 
		ModelImporter importer = importers.get(extension);
		if(importer != null) {
			FileHandler.copyFileToPersistentMemory(importer.toObsidianFile(file));
			return importer.fromFile(file, clazz);
		}
		throw new MissingImporterException(extension);
	}

	public static void saveModelDataToObsidianFile(ModelObj model, File obsidianFile) 
	{
		try 
		{
			NBTTagCompound nbt = model.createNBTTag();

			File nbtFile = new File(SETUP_NAME);
			CompressedStreamTools.write(nbt, nbtFile);

			FileUtils.addEntryToExistingZip(obsidianFile, nbtFile);
			nbtFile.delete();
		}
		catch(Exception e) 
		{
			System.err.println("Could not save model data for " + model.entityName);
			e.printStackTrace();
		}
	}
}
