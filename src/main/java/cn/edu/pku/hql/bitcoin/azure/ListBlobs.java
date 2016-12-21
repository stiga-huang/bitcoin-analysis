package cn.edu.pku.hql.bitcoin.azure;

import com.microsoft.azure.storage.*;
import com.microsoft.azure.storage.blob.*;

/**
 * Created by huangql on 12/21/16.
 */
public class ListBlobs {
    public static final String STORAGE_CONNECTION_STRING =
            "DefaultEndpointsProtocol=http;AccountName=infomall;AccountKey=" +
                    "WA71qwR/C+JQAfQ/a4BrABb3wqm/nnbJYbXi7WmHH2wW6n2IzjJVkBP/gamgTvenm+4Bt7jeRMbJThefHXauUA==;" +
                    "BlobEndpoint=https://infomall.blob.core.chinacloudapi.cn";

    public static void main(String[] args) {
        try {
            // Retrieve storage account from connection-string.
            CloudStorageAccount storageAccount = CloudStorageAccount.parse(STORAGE_CONNECTION_STRING);

            // Create the blob client.
            CloudBlobClient blobClient = storageAccount.createCloudBlobClient();

            for (CloudBlobContainer container : blobClient.listContainers()) {
                for (ListBlobItem blobItem : container.listBlobs()) {
                    System.out.println(blobItem.getUri());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
