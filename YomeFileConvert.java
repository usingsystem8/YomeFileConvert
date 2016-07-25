import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class YomeFileConvert {
	public static void main(String[] args) {
		YomeFileConvert exe = new YomeFileConvert();
		exe.execute();
	}
	public void execute(){
		String voice_dir_in = "/Users/yuta/Desktop/voice/";
		String voice_dir_out = "/Users/yuta/Desktop/voice_conv/";
		try {
			convVoice(voice_dir_in,voice_dir_out);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	private void convVoice(String voice_dir_in,String voice_dir_out) throws IOException, InterruptedException{
		convData(voice_dir_in, voice_dir_out);
	}
	private void convData(String voice_dir_in, String voice_dir_out) throws IOException, InterruptedException {
		for (int i = 1; i < 48; i++){
			System.out.println(voice_dir_in + i + ".mp3 --> " + voice_dir_out + i + ".mp3");
			convFile(voice_dir_in + i + ".mp3",voice_dir_out + i + ".mp3");
		}
	}
	private void convFile(String input_file_name,String out_file_name) {
		FileInputStream fip = null;
		FileOutputStream fop = null;
		try {
			System.out.println("Started Conversion...");
			fip = new FileInputStream(input_file_name);
			fop = new FileOutputStream(out_file_name);
			long fsize = (new File(input_file_name)).length();
			int m = 0x80;
			for(int i = 0; i < fsize; i++, m++) {
				if( m == 0xFF ) {
					m = 0x80;
				}
				int t = fip.read();
				t = t^m;
				fop.write(t);
			}
			System.out.println("Finished: " + fsize + "Bytes");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(fip != null){
					fip.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(fop != null){
					fop.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
