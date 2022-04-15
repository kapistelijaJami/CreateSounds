package createsounds;

import java.io.IOException;
import java.io.OutputStream;

public class SaveToWav {
	
	/**
	* Write PCM data as WAV file
	* @param os  Stream to save file to
	* @param data  8 bit PCMData
	* @param sampleRate  Sample rate - 8000, 16000, etc.
	* @param channels Number of channels - Mono = 1, Stereo = 2, etc..
	* @param bitsPerSample Number of bits per sample (16 here)
	* @throws IOException
	*/
   public static void PCMtoFile(OutputStream os, byte[] data, int sampleRate, int channels, int bitsPerSample) throws IOException {
	   try (os) {
		   byte[] header = new byte[44];
		   
		   long totalDataLen = data.length + 36;
		   long bitrate = sampleRate * channels * bitsPerSample;
		   
		   header[0] = 'R';
		   header[1] = 'I';
		   header[2] = 'F';
		   header[3] = 'F';
		   header[4] = (byte) (totalDataLen & 0xff);
		   header[5] = (byte) ((totalDataLen >> 8) & 0xff);
		   header[6] = (byte) ((totalDataLen >> 16) & 0xff);
		   header[7] = (byte) ((totalDataLen >> 24) & 0xff);
		   header[8] = 'W';
		   header[9] = 'A';
		   header[10] = 'V';
		   header[11] = 'E';
		   header[12] = 'f';
		   header[13] = 'm';
		   header[14] = 't';
		   header[15] = ' ';
		   header[16] = (byte) bitsPerSample;
		   header[17] = 0;
		   header[18] = 0;
		   header[19] = 0;
		   header[20] = 1;
		   header[21] = 0;
		   header[22] = (byte) channels;
		   header[23] = 0;
		   header[24] = (byte) (sampleRate & 0xff);
		   header[25] = (byte) ((sampleRate >> 8) & 0xff);
		   header[26] = (byte) ((sampleRate >> 16) & 0xff);
		   header[27] = (byte) ((sampleRate >> 24) & 0xff);
		   header[28] = (byte) ((bitrate / 8) & 0xff);
		   header[29] = (byte) (((bitrate / 8) >> 8) & 0xff);
		   header[30] = (byte) (((bitrate / 8) >> 16) & 0xff);
		   header[31] = (byte) (((bitrate / 8) >> 24) & 0xff);
		   header[32] = (byte) ((channels * bitsPerSample) / 8);
		   header[33] = 0;
		   header[34] = 16;
		   header[35] = 0;
		   header[36] = 'd';
		   header[37] = 'a';
		   header[38] = 't';
		   header[39] = 'a';
		   header[40] = (byte) (data.length  & 0xff);
		   header[41] = (byte) ((data.length >> 8) & 0xff);
		   header[42] = (byte) ((data.length >> 16) & 0xff);
		   header[43] = (byte) ((data.length >> 24) & 0xff);
		   
		   os.write(header, 0, 44);
		   os.write(data);
	   }
   }
   
	public static byte[] get16BitPcm(short[] data) {
		byte[] resultData = new byte[2 * data.length];
		int iter = 0;
		for (double sample : data) {
			short maxSample = (short)((sample * Short.MAX_VALUE));
			resultData[iter++] = (byte)(maxSample & 0x00ff);
			resultData[iter++] = (byte)((maxSample & 0xff00) >>> 8);
		}
		return resultData;
	}
}
