package group.sneakers.sneakers.service.sales;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import group.sneakers.sneakers.model.sales.Sales;

@Service
public class TokenService {
    private static final Logger logger = LoggerFactory.getLogger(TokenService.class);

    private final ObjectMapper mapper = new ObjectMapper();
    private final File file = new File("tokens.json");

    public static class SaleToken {
        public String token;
        public Integer salesId;
        public Date createdAt;

        public SaleToken() { }

        public SaleToken(String token, Integer salesId, Date createdAt) {
            this.token = token;
            this.salesId = salesId;
            this.createdAt = createdAt;
        }
    }

    public synchronized String generateAndStoreToken(Sales sales) {
        try {
            String token = UUID.randomUUID().toString();
            SaleToken st = new SaleToken(token, sales.getId(), new Date());

            List<SaleToken> list = new ArrayList<>();
            if (file.exists()) {
                try {
                    CollectionType listType = mapper.getTypeFactory().constructCollectionType(List.class, SaleToken.class);
                    list = mapper.readValue(file, listType);
                } catch (IOException e) {
                    // If the file cannot be read, start fresh
                    list = new ArrayList<>();
                }
            }
            list.add(st);
            try {
                mapper.writerWithDefaultPrettyPrinter().writeValue(file, list);
            } catch (IOException e) {
                logger.error("Unable to write tokens file {}", file.getAbsolutePath(), e);
            }
            return token;
        } catch (Exception e) {
            logger.error("Unexpected error generating token, returning fallback token: {}", e.getMessage(), e);
            return UUID.randomUUID().toString();
        }
    }

    public String findTokenBySalesId(Integer salesId) {
        try {
            if (!file.exists()) return null;
            CollectionType listType = mapper.getTypeFactory().constructCollectionType(List.class, SaleToken.class);
            List<SaleToken> list = mapper.readValue(file, listType);
            for (SaleToken st : list) {
                if (st.salesId.equals(salesId)) return st.token;
            }
            return null;
        } catch (IOException e) {
            throw new RuntimeException("Unable to read token file", e);
        }
    }

    public List<SaleToken> findAllTokens() {
        try {
            if (!file.exists()) return new ArrayList<>();
            CollectionType listType = mapper.getTypeFactory().constructCollectionType(List.class, SaleToken.class);
            List<SaleToken> list = mapper.readValue(file, listType);
            return list;
        } catch (IOException e) {
            throw new RuntimeException("Unable to read token file", e);
        }
    }

}
