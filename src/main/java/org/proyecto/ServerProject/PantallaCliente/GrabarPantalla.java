package org.proyecto.ServerProject.PantallaCliente;

import org.jcodec.api.awt.AWTSequenceEncoder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class GrabarPantalla implements Runnable {
    private AWTSequenceEncoder encoder;
    private BlockingQueue<BufferedImage> frameQueue;
    private boolean isRecording;
    private String outputFilePath;

    public GrabarPantalla(String outputFilePath) {
        this.outputFilePath = outputFilePath;
        this.frameQueue = new LinkedBlockingQueue<>();
        this.isRecording = true;
    }

    public void agregarFrame(BufferedImage frame) {
        if (isRecording) {
            frameQueue.offer(frame);
        }
    }

    public void detenerGrabacion() {
        isRecording = false;
        try {
            if (encoder != null) {
                encoder.finish();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public BufferedImage normalizarFrame(BufferedImage frame, int width, int height) {
        BufferedImage normalizedFrame = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = normalizedFrame.createGraphics();
        g2d.drawImage(frame, 0, 0, width, height, null);
        g2d.dispose();
        return normalizedFrame;
    }

    @Override
    public void run() {
        try {
            File outputFile = new File(outputFilePath);
            encoder = AWTSequenceEncoder.createSequenceEncoder(outputFile, 25); // 25 FPS
            while (isRecording || !frameQueue.isEmpty()) {
                BufferedImage frame = frameQueue.poll();
                if (frame != null) {
                    encoder.encodeImage(frame);
                }
            }
            encoder.finish();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
