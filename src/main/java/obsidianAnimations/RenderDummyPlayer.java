package obsidianAnimations;

import java.io.IOException;

import net.minecraft.util.ResourceLocation;
import obsidianAPI.render.ModelObj;
import obsidianAPI.render.RenderObj;

public class RenderDummyPlayer extends RenderObj
{

	private static final ResourceLocation modelRL = new ResourceLocation("mod_obsidian_animations:models/player/Player.obm");
	private static final ResourceLocation textureRL = new ResourceLocation("mod_obsidian_animations:models/player/Player.png");
	
	public RenderDummyPlayer() throws IOException 
	{
		super(new ModelObj("dummy", modelRL, textureRL), null);
	}

}
