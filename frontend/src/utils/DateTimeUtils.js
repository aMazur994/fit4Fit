class DateTimeUtils {
    static defaultFormatDateTime(str) {
        const tokens = str.split("T");

        let date = tokens[0];
        let time = tokens[1].split(".")[0];

        return `${date} ${time}`;
    }
}

export default DateTimeUtils;
