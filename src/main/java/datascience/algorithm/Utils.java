package datascience.algorithm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import datascience.data.DataFrame;

public class Utils {
	public static double infoGain(String target, String feature,
			DataFrame dataFrame) {
		double entropyBefore = entropy(target, dataFrame);
		int count = dataFrame.count();
		List<Object> distinct = dataFrame.distinct(feature);
		Iterator<Object> iterator = distinct.iterator();
		double entropyAfter = 0.0;
		while (iterator.hasNext()) {
			Object next = iterator.next();
			DataFrame filterDataFrame = dataFrame.filter(feature, next);
			int filterCount = filterDataFrame.count();
			double probability = filterCount * 1.0 / count;
			entropyAfter += probability * entropy(target, filterDataFrame);
		}
		double infoGain = entropyBefore - entropyAfter;
		return infoGain;

	}

	public static double entropy(String column, DataFrame dataFrame)
			throws ArithmeticException {
		List<Object> uniqueValues = dataFrame.distinct(column);
		int count = dataFrame.count();
		double entropy = 0;
		Iterator<Object> iterator = uniqueValues.iterator();
		while (iterator.hasNext()) {
			Object next = iterator.next();
			int occurrence = dataFrame.filter(column, next).count();
			double probability = occurrence * 1.0 / count;
			entropy -= probability * log2(probability);
		}
		return entropy;

	}

	public static double log2(double num) {
		double logResult = Math.log(num) * 1.0 / Math.log(2.0);
		return logResult;

	}

	public static int maxIndex(List<Double> list) throws UtilsException {
		double max = 0;
		if (list.size() == 0) {
			throw new UtilsException("List of size zero");
		}
		Iterator<Double> iterator = list.iterator();
		while (iterator.hasNext()) {
			Double next = iterator.next();
			if (max < next) {
				max = next;
			}
		}
		int maxIndex = list.indexOf(max);
		return maxIndex;
	}

	public static Object commonClass(DataFrame dataFrame, String target) {
		List<Object> distinct = dataFrame.distinct(target);
		Iterator<Object> iterator = distinct.iterator();
		int maxOccurence = 0;
		Object common = null;
		while (iterator.hasNext()) {
			Object next = iterator.next();
			int count = dataFrame.filter(target, next).count();
			if (count > maxOccurence) {
				maxOccurence = count;
				common = next;
			}

		}
		return common;

	}

	public static FeatureInfoGain maxInfoGainFeature(DataFrame dataFrame,
			List<String> features, String target) {
		Iterator<String> iterator = features.iterator();
		List<Double> infoGains = new ArrayList<Double>();
		while (iterator.hasNext()) {
			String next = iterator.next();
			infoGains.add(Utils.infoGain(target, next, dataFrame));
		}
		int maxIndex = Utils.maxIndex(infoGains);
		String feature = features.get(maxIndex);
		double infoGain = infoGains.get(maxIndex);
		return new FeatureInfoGain(feature, infoGain);

	}

}
