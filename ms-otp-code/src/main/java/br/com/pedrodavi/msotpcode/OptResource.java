package br.com.pedrodavi.msotpcode;

import com.eatthepath.otp.TimeBasedOneTimePasswordGenerator;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;

@Path("/v1/otp")
public class OptResource {

    @GET
    @Path("/gen")
    public String genOtp() throws NoSuchAlgorithmException, InvalidKeyException {

        final TimeBasedOneTimePasswordGenerator totp = new TimeBasedOneTimePasswordGenerator();

        final Key key;
        {
            final KeyGenerator keyGenerator = KeyGenerator.getInstance(totp.getAlgorithm());

            // Key length should match the length of the HMAC output (160 bits for SHA-1, 256 bits
            // for SHA-256, and 512 bits for SHA-512). Note that while Mac#getMacLength() returns a
            // length in _bytes,_ KeyGenerator#init(int) takes a key length in _bits._
            final int macLengthInBytes = Mac.getInstance(totp.getAlgorithm()).getMacLength();
            keyGenerator.init(macLengthInBytes * 8);

            key = keyGenerator.generateKey();
        }

        final Instant now = Instant.now();
        final Instant later = now.plus(totp.getTimeStep());

        return "Current: " + totp.generateOneTimePasswordString(key, now) + " Future: " + totp.generateOneTimePasswordString(key, later);
    }

}