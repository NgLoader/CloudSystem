package de.ng.cloud.master.template;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.ng.cloud.core.util.FileUtil;

public class TemplateManager {
	
	public static final TemplateManager TEMPLATE_MANAGER = new TemplateManager();
	
	private List<Template> templates;
	
	public TemplateManager() {
		templates = new ArrayList<>();
	}
	
	public void load() {
		if(!new File("./Cloud/templates").exists())
			try {
				System.out.println("Template folder not found. generating with example template...");
				FileUtil.copyLocalResourceFolderToFolder("cloud/templates", "./Cloud/templates");
			} catch (IOException ex) {
				System.out.println("Failed to create templates folder.");
				ex.printStackTrace();
			}
		
		for (File file : new File("Cloud/templates").listFiles())
			if (file.isDirectory()) {
				try {
					System.out.println("Template \"" + file.getName() + "\" loading...");
					
					Template template = new Template(file);
					if(template.load()) {
						templates.add(template);
						System.out.println("Template \"" + file.getName() + "\" loaded.");
					} else
						System.out.println("Failed to load tempalte. Please check the template \"" + file.getPath() + "\"");
				} catch(Exception ex) {
					System.out.println("Failed to load template \"" + file.getPath() + "\"");
				}
			}
	}
	
	public void reload() {
		templates.clear();
		load();
	}
	
	public void add(Template template) {
		if(!templates.contains(template))
			templates.add(template);
	}
	
	public void remove(Template template) {
		if(templates.contains(template))
			templates.remove(template);
	}
	
	public List<Template> getTemplates() {
		return templates;
	}
}