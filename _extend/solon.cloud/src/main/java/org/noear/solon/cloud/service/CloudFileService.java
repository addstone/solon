package org.noear.solon.cloud.service;

import org.noear.solon.Utils;
import org.noear.solon.cloud.exception.CloudFileException;
import org.noear.solon.core.handle.Result;

import java.io.*;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

/**
 * 云端文件服务（事件总线服务）
 *
 * @author noear
 * @since 1.3
 */
public interface CloudFileService {
    /**
     * 获取文本
     */
    default String getText(String bucket, String key) throws CloudFileException {
        return Utils.getString(getStream(bucket, key), "UTF-8");
    }

    /**
     * 获取文本
     */
    default String getText(String key) throws CloudFileException {
        return getText(null, key);
    }

    /**
     * 推入文本
     */
    default Result putText(String bucket, String key, String text) throws CloudFileException{
        InputStream stream = new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8));
        return putStream(bucket, key, stream, null);
    }

    /**
     * 推入文本
     */
    default Result putText(String key, String text) throws CloudFileException {
        return putText(null, key, text);
    }

    /**
     * 获取文本
     */
    InputStream getStream(String bucket, String key) throws CloudFileException;

    /**
     * 获取文本
     */
    default InputStream getStream(String key) throws CloudFileException {
        return getStream(null, key);
    }

    /**
     * 推入文件
     */
    Result putStream(String bucket, String key, InputStream stream, String streamMime) throws CloudFileException;

    /**
     * 推入文件
     */
    default Result putStream(String key, InputStream stream, String streamMime) throws CloudFileException {
        return putStream(null, key, stream, streamMime);
    }
}
