package seedu.address.logic.commands;

import java.time.LocalDate;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class FilterRoomCommand extends Command {
    public static final String COMMAND_WORD = "filterRoom";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters rooms that are of the indicated room type "
            + "(if applicable) and are not occupied within the start and end date. \n"
            + "To filter single rooms, input 1 as the parameter for typ/ \n"
            + "Parameters: "
            + "sd/ [STARTDATE] ed/ [ENDDATE] typ/ [ROOMTYPE] (optional) \n"
            + "Example: " + COMMAND_WORD + " typ/2 sd/2020-11-12 ed/2020-11-15";
    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "Remark command not implemented yet";
    public static final String MESSAGE_SUCCESS = "Successfully filtered available rooms: \n%s";

    private final LocalDate startDate;
    private final LocalDate endDate;
    private final int roomType;

    /**
     * Creates a FilterRoomCommand.
     */
    public FilterRoomCommand(LocalDate startDate, LocalDate endDate, int roomType) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.roomType = roomType;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        ObservableList<Integer> unavailableRooms = model.getUnavailableRooms(startDate, endDate);
        ObservableList<Integer> availableRooms = model.getAvailableRooms(unavailableRooms);
        String result = "";
        if (this.roomType == 0) {
            result = model.displayRooms(availableRooms);
        } else if (this.roomType == 1) {
            result = model.displaySingleRooms(availableRooms);
        } else if (this.roomType == 2) {
            result = model.displayDoubleRooms(availableRooms);
        } else {
            result = model.displaySuiteRooms(availableRooms);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, result));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof FilterRoomCommand)) {
            return false;
        }
        FilterRoomCommand e = (FilterRoomCommand) other;
        return this.startDate.isEqual(e.startDate)
                && this.endDate.isEqual(e.endDate)
                && this.roomType == e.roomType;
    }
}
