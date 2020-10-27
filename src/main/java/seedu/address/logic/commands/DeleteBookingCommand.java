package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.logic.LogicManager;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.booking.Booking;

/**
 * Deletes a booking identified using it's displayed index from the address book.
 */
public class DeleteBookingCommand extends Command {

    public static final String COMMAND_WORD = "deleteBooking";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the booking identified by bookingId.\n"
            + "Parameters: BOOKING_ID (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_BOOKING_SUCCESS = "Deleted Booking: %1$s";

    private final Integer bookingId;

    private final Logger logger = LogsCenter.getLogger(LogicManager.class);



    public DeleteBookingCommand(Integer bookingId) {
        this.bookingId = bookingId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        logger.info("=============================[ Executing deleteBooking ]===========================");
        requireNonNull(model);

        assert bookingId > 0;
        if (!model.hasBookingWithId(bookingId)) {
            logger.warning("Non-existent bookingId");
            throw new CommandException(Messages.MESSAGE_INVALID_BOOKING_ID);
        }

        Booking bookingToDelete = model.getBookingWithId(bookingId);
        model.deleteBooking(bookingToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_BOOKING_SUCCESS, bookingToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteBookingCommand // instanceof handles nulls
                && bookingId.equals(((DeleteBookingCommand) other).bookingId)); // state check
    }
}
