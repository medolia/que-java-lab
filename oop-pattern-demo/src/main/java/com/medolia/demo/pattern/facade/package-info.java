/**
 * @author lbli@trip.com
 * @since 0.0.1
 *
 * <p>视频格式转换过程中，需要经历格式读取、格式转换、音频修正，这些过程相关的过程库较为复杂；
 * <p>考虑使用外观模式，VideoConversionFacade 负责所有的复杂库里的交互，而客户端只调用这个类的 convertVideo 方法</p>
 */
package com.medolia.demo.pattern.facade;